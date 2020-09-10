package br.com.bottelegram.comando.faculdade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.faculdade.dto.InsereDados;
import bancodedados.faculdade.dto.LOGFaculdade;
import br.com.bottelegram.EscopoApplictCSCTimerTelegram;
import br.com.bottelegram.FluxoTelegram;
import br.com.bottelegram.comando.dto.InteracaoComando;

//curl -F "url=" 
// https://api.telegram.org/1015053732:AAHWzTrMTCCSEmjoFELpVT8XYcbOQH6dvB4/setWebhook
// https://api.telegram.org/1015053732:AAHWzTrMTCCSEmjoFELpVT8XYcbOQH6dvB4/setupdates
// https://api.telegram.org/1015053732:AAHWzTrMTCCSEmjoFELpVT8XYcbOQH6dvB4/getUpdates

//https://web.telegram.org/#/im?p=@cscpr_bot
public class FluxoTelegramFaculdade extends FluxoTelegram {
	private static final String ESTACIO_PNG = "estacio.png";
	private int offSetAtributo = 0;

	/*
	 * private Integer getUserId( final Update update ) {
	 * 
	 * if ( update.message() != null ) { return update.message().from().id(); } else
	 * if ( update.getCallback_query() != null ) { return
	 * update.getCallback_query().getFrom().id(); } else if (
	 * update.chosenInlineResult() != null ) { return
	 * update.chosenInlineResult().from().id(); } else if ( update.inlineQuery() !=
	 * null ) { return update.inlineQuery().from().id(); }
	 * 
	 * return -1; }
	 */
	public void iniciarChatBotTelegram() {
		GetUpdatesResponse updatesResponse;
		SendResponse sendResponse = null;
		BaseResponse baseResponse;
		updatesResponse = this.botTelegram.execute(new GetUpdates().limit(100).offset(this.offSetAtributo));
		List<Update> updates = updatesResponse.updates();

		//
		// Conflict: can't use getUpdates method while webhook is active; use
		// deleteWebhook to delete the webhook first
		SetWebhook request = new SetWebhook()
				.url("https://api.telegram.org/1015053732:AAHWzTrMTCCSEmjoFELpVT8XYcbOQH6dvB4/setWebhook")
				.certificate(new byte[] {}); // byte[]
//			       .certificate(new File("path")); // or file 
		BaseResponse response = this.botTelegram.execute(request);
		boolean ok = response.isOk();
		//
		if (updates != null) {
			for (Update update : updates) {
				InteracaoComando dadosEntrada = validarComandoRecebidoFaculdade(update);
				MenuGraficoFaculdade menu = new MenuGraficoFaculdade();

				if (dadosEntrada != null) {

					System.out.println(CentralMensagensBrewField.DIVISAO_TRACO_INICIO);
					System.out.println(dadosEntrada);
					System.out.println(CentralMensagensBrewField.DIVISAO_TRACO);
					this.offSetAtributo = update.updateId() + 1;
					// envio de "Escrevendo" antes de enviar a resposta
					baseResponse = this.botTelegram
							.execute(new SendChatAction(dadosEntrada.getIdUsuarioTelegram(), ChatAction.typing.name()));
					System.out.println("Escrevendo ..." + baseResponse.isOk());
					System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

					StringBuffer msgRetornadaCliente = new StringBuffer();
					msgRetornadaCliente.append("\nOlá " + dadosEntrada.getNome());
					msgRetornadaCliente.append(
							"\n<b>Vou te ajudar a esclarecer sua dúvida.</b>\nEscolha sua opção no menu principal.");
//					msgRetornadaCliente.append(
//							"\nEm seguida avalie a sua dúvida clicando nos botões do menu secundário apresentados após escolher sua opção.");
					msgRetornadaCliente.append("\nSugestões enviar e-mail a <b>douglas.mendes@estacio.br</b>");

					if (dadosEntrada.getIdComandoPai() == 0 && dadosEntrada.getIdComando() == 0) {
						this.botTelegram.execute(new SendPhoto(dadosEntrada.getIdUsuarioTelegram(),
								new java.io.File(EscopoApplictCSCTimerTelegram.obterPathWEB() + ESTACIO_PNG)));
						menu.menuNivel01(dadosEntrada.getIdUsuarioTelegram());
						menu.zerarBotoesBaixo(dadosEntrada.getIdUsuarioTelegram());
					} else if (dadosEntrada.getIdComandoPai() == 0) {
						menu.menuNivel01(dadosEntrada.getIdUsuarioTelegram());
						processarDuvida(dadosEntrada, dadosEntrada.getIdUsuarioTelegram(), dadosEntrada.getIdComando());
					} else {
						msgRetornadaCliente.append(dadosEntrada.getComplementoComando());
						msgRetornadaCliente
								.append(processarDetalheDuvida(dadosEntrada, dadosEntrada.getIdUsuarioTelegram(),
										dadosEntrada.getIdComandoPai(), dadosEntrada.getIdComando()));
						InsereDados ins = new InsereDados();
						LOGFaculdade logDTO = new LOGFaculdade(dadosEntrada.getIdUsuarioTelegram(), 
								dadosEntrada.getNome(), dadosEntrada.getIdComandoPai(), 
								dadosEntrada.getIdComando(), dadosEntrada.getComplementoComando(),
								new Date());
						ins.inserirLOG(logDTO);
					}
					sendResponse = this.botTelegram.execute(
							new SendMessage(dadosEntrada.getIdUsuarioTelegram(), msgRetornadaCliente.toString())
									.parseMode(ParseMode.HTML));
					System.out.println("Mensagem Enviada:" + sendResponse.isOk() + "Conteudo: " + msgRetornadaCliente);
				} else {
					this.botTelegram.execute(new SendMessage(update.message().chat().id(), ""));
					updatesResponse.updates().clear();
					this.offSetAtributo = update.updateId() + 1;
					menu.menuNivel01(update.message().chat().id());
					return;
				}
			}
		}
	}

	protected InteracaoComando validarComandoRecebidoFaculdade(Update update) {
		InteracaoComando dadosEntrada = new InteracaoComando();
		try {
			String complemento;

			Message msgTelegram = update.message();
			if (msgTelegram != null) {
				complemento = update.message().text();
			} else {
				msgTelegram = update.callbackQuery().message();
				complemento = update.callbackQuery().data();
			}
			if (complemento != null && complemento.contains("-") && complemento.length() == 3) {
				String vet[] = complemento.split("-");
				dadosEntrada.setIdComandoPai(Integer.parseInt(vet[0]));
				dadosEntrada.setIdComando(Integer.parseInt(vet[1]));
			} else if (complemento != null && complemento.contains("-") && complemento.length() >= 3) {
				String vet[] = complemento.split("-");
				dadosEntrada.setIdComandoPai(Integer.parseInt(vet[0]));
				dadosEntrada.setIdComando(Integer.parseInt(vet[1]));
				dadosEntrada.setComplementoComando(vet[2]);
			} else {
				dadosEntrada.setIdComando(0);
				dadosEntrada.setIdComandoPai(0);
			}
			System.out.println("Complemento: " + complemento);
			dadosEntrada.setIdUsuarioTelegram(msgTelegram.chat().id());
			dadosEntrada.setNome(msgTelegram.chat().firstName());
			dadosEntrada.setMeuContato(msgTelegram.contact());

		} catch (Exception e) {
			System.out.println("Opção invalida ou Botao opcoes sem valor.");
		}
		return dadosEntrada;
	}

	private void processarDuvida(InteracaoComando dadosEntrada, long idUsuario, int opcao) {
		FaqEstacio faq = new FaqEstacio();
		MenuGraficoFaculdade menu = new MenuGraficoFaculdade();
		List<FAQ> ret;

		switch (opcao) {
		case FaqEstacio.ACADEMICO_ID:
			ret = faq.carregarNivelAcademico();
			menu.botoesNivel02(idUsuario, ret);
			break;
		case FaqEstacio.SECRETARIA_ID:
			ret = faq.carregarNivelSecretaria();
			menu.botoesNivel02(idUsuario, ret);
			break;
		case FaqEstacio.FINANCEIRO_ID:
			ret = faq.carregarNivelFinanceiro();
			menu.botoesNivel02(idUsuario, ret);
			break;
		case FaqEstacio.BOLETO_ID:
			ret = faq.carregarNivelBoleto();
			menu.botoesNivel02(idUsuario, ret);
			break;
		case FaqEstacio.GRADE_ID:
			ret = faq.carregarNivelGrade();
			menu.botoesNivel02(idUsuario, ret);
			break;
		case FaqEstacio.BIBLIOTECA_ID:
			ret = faq.carregarNivelBiblioteca();
			menu.botoesNivel02(idUsuario, ret);
			break;
		case FaqEstacio.COORDENACAO_ADS_ID:
			ret = faq.carregarNivelCoordenacaoADS();
			menu.botoesNivel02(idUsuario, ret);
			break;
		case FaqEstacio.DATAS_IMPORTANTES_ID:
			ret = faq.carregarNivelDataImportantes();
			menu.botoesNivel02(idUsuario, ret);
			break;
		// novo 04
		case FaqEstacio.EMPREGOS_ID:
			ret = faq.carregarNivelEmpregos();
			menu.botoesNivel02(idUsuario, ret);
			break;

		default:
			break;
		}

	}

	private String processarDetalheDuvida(InteracaoComando dadosEntrada, long idUsuario, int idPai, int idOpcao) {
		FaqEstacio faq = new FaqEstacio();
		MenuGraficoFaculdade menu = new MenuGraficoFaculdade();
		List<FAQ> ret = new ArrayList<>();
		String respDuvida = "";
		switch (idPai) {
		case FaqEstacio.ACADEMICO_ID:
			ret = faq.carregarNivelAcademico();
			break;
		case FaqEstacio.SECRETARIA_ID:
			ret = faq.carregarNivelSecretaria();
			break;
		case FaqEstacio.FINANCEIRO_ID:
			ret = faq.carregarNivelFinanceiro();
			break;
		case FaqEstacio.BOLETO_ID:
			ret = faq.carregarNivelBoleto();
			break;
		case FaqEstacio.GRADE_ID:
			ret = faq.carregarNivelGrade();
			break;
		case FaqEstacio.BIBLIOTECA_ID:
			ret = faq.carregarNivelBiblioteca();
			break;
		case FaqEstacio.COORDENACAO_ADS_ID:
			ret = faq.carregarNivelCoordenacaoADS();
			break;
		case FaqEstacio.DATAS_IMPORTANTES_ID:
			ret = faq.carregarNivelDataImportantes();
			break;

		// novo 5
		case FaqEstacio.EMPREGOS_ID:
			ret = faq.carregarNivelEmpregos();
			break;

		default:
			break;
		}
		for (int i = 0; i < ret.size(); i++) {
			if (idOpcao == ret.get(i).getIdItem()) {
				respDuvida = ret.get(i).getConteudoAluno();
				if (ret.get(i).isPossuiArquivo()) {
					String arquivo = EscopoApplictCSCTimerTelegram.obterPathWEBFile() + ret.get(i).getNomeArquivo();
					this.botTelegram
							.execute(new SendDocument(dadosEntrada.getIdUsuarioTelegram(), new java.io.File(arquivo)))
							.message();
				}
				break;
			}
		}

		return respDuvida;
	}

	public void maaaaaain(String[] args) {
//		String caption = "caption <b>bold</b>";
//		this.botTelegram.execute(
//                new SendDocument(1, new java.io.File("")).fileName("").caption(caption).parseMode(ParseMode.HTML))
//                .message();		

	}
}
