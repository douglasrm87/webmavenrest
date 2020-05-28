package br.com.bottelegram;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Location;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ItemPedidoDTO;
import br.com.bottelegram.comando.brewfield.CentralComandoTelegramBrewField;
import br.com.bottelegram.comando.dto.InteracaoComando;

//https://web.telegram.org/#/im?p=@cscpr_bot
public class FluxoTelegram {

	public static final Logger logger = Logger.getLogger(FluxoTelegram.class);

	TelegramBot botTelegram = new TelegramBot(CentralMensagensBrewField.TOKEN_TELEGRAM_FACULDADE);
	int offSetAtributo = 0;

	public void menuNrTelefone(long idUsuario, String nomeUsuario) {
		KeyboardButton numeroCelular = new KeyboardButton(CentralMensagensBrewField.MEU_CELULAR).requestContact(true);
		KeyboardButton[] vetorBotoes = { numeroCelular };
		Keyboard teclado = new ReplyKeyboardMarkup(vetorBotoes).oneTimeKeyboard(true).resizeKeyboard(true)
				.selective(true);
		SendMessage msgTelegram = new SendMessage(idUsuario,
				nomeUsuario + CentralMensagensBrewField.PRECISO_CADASTRAR_SEU_CELULAR
						+ CentralMensagensBrewField.MEU_CELULAR).parseMode(ParseMode.HTML).disableWebPagePreview(false)
								.replyMarkup(teclado);
		SendResponse sendResponse = this.botTelegram.execute(msgTelegram);
	}

	private String getFileUrl(final String fileId) {

		final GetFile getFile = new GetFile(fileId);
		final GetFileResponse response = this.botTelegram.execute(getFile);
		final File file = response.file();
		if (file != null) {
			System.out.println(file.fileId());
			System.out.println(file.filePath()); // relative path
			System.out.println(file.fileSize());
			final String url = this.botTelegram.getFullFilePath(file);
			return url;
		}
		return CentralMensagensBrewField.RECIBO_INVÁLIDO_CONTACTAR_BREW_FIELD;
	}

	public void iniciarChatBotTelegram() {
		GetUpdatesResponse updatesResponse;
		SendResponse sendResponse = null;
		BaseResponse baseResponse;

		// executa comando no Telegram para obter as mensagens pendentes a partir de um
		// off-set (limite inicial)
		updatesResponse = this.botTelegram.execute(new GetUpdates().limit(100).offset(this.offSetAtributo));

		// lista de mensagens
		List<Update> updates = updatesResponse.updates();

		// análise de cada ação da mensagem
		if (updates != null) {
			for (Update update : updates) {
				Long idUsuario = update.message().chat().id();
				String nomeUsuario = update.message().chat().firstName();
				String sobreNomeUsuario = update.message().chat().lastName();
				String mensagem = update.message().text();
				Location localidade = update.message().location();
				Contact meuContato = update.message().contact();
				System.out.println(CentralMensagensBrewField.DIVISAO_TRACO_INICIO);
				System.out.println("nomeUsuario: " + nomeUsuario);
				System.out.println("idUsuario: " + idUsuario);
				System.out.println("sobreNomeUsuario: " + sobreNomeUsuario);
				System.out.println("mensagem: " + mensagem);
				System.out.println("Data: " + new Date());
				System.out.println(CentralMensagensBrewField.DIVISAO_TRACO);
				PhotoSize[] fotoPagamento = update.message().photo();

				logger.info("Contato de:");
				logger.info("NomeUsuario: " + nomeUsuario);
				logger.info("SobreNomeUsuario: " + sobreNomeUsuario);
				logger.info("Mensagem: " + mensagem);
				boolean retComando = validarComandoRecebido(update.message());
				int idComando = 0;
				InteracaoComando usuarioExistente = null;

				usuarioExistente = EscopoApplictCSCTimerTelegram.mapaClienteComando.get(idUsuario);

				if (retComando) {
					idComando = Integer.parseInt(mensagem);
					if (usuarioExistente == null) {
						EscopoApplictCSCTimerTelegram.mapaClienteComando.put(idUsuario,
								new InteracaoComando(idUsuario, nomeUsuario, sobreNomeUsuario, idComando, null));
					} else {
						usuarioExistente.setIdComando(idComando);
					}
				} else {
					if (usuarioExistente != null) {
						usuarioExistente.setComplementoComando(update.message().text());
						idComando = usuarioExistente.getIdComando();
					}
				}
				// atualização do off-set
				this.offSetAtributo = update.updateId() + 1;

				logger.info("Recebendo mensagem:" + mensagem);
				logger.info("Nome usuario Telegram:" + nomeUsuario);

				// envio de "Escrevendo" antes de enviar a resposta
				baseResponse = this.botTelegram.execute(new SendChatAction(idUsuario, ChatAction.typing.name()));
				logger.info("Escrevendo ..." + baseResponse.isOk());
				// verificação de ação de chat foi enviada com sucesso
				logger.info("Resposta de Chat Action Enviada?" + baseResponse.isOk());

				// envio da mensagem de resposta
				String msgRetornadaCliente = "";
				if (usuarioExistente != null) {
					if (fotoPagamento != null) {
						usuarioExistente.setEnviadoFoto(true);
						String urlRecibo = getFileUrl(fotoPagamento[0].fileId());
						usuarioExistente.setUrlRecibo(urlRecibo);
					}
					msgRetornadaCliente = processarComandoGeralBrewField(usuarioExistente);
					EscopoApplictCSCTimerTelegram.mapaClienteComando.get(usuarioExistente.getIdUsuario())
							.setCliente(usuarioExistente.getCliente());
					EscopoApplictCSCTimerTelegram.mapaClienteComando.get(usuarioExistente.getIdUsuario())
							.setComplementoComando(usuarioExistente.getComplementoComando());
				} else {
					usuarioExistente = new InteracaoComando(idUsuario, nomeUsuario, sobreNomeUsuario, idComando, null);
					if (meuContato == null && usuarioExistente.getCliente() == null) {
						menuNrTelefone(idUsuario, nomeUsuario);
					} else {
						usuarioExistente.setMeuContato(meuContato);
					}
					msgRetornadaCliente = processarComandoGeralBrewField(usuarioExistente);
					if (msgRetornadaCliente != null && msgRetornadaCliente.length() > 0) {
						EscopoApplictCSCTimerTelegram.mapaClienteComando.put(idUsuario,
								new InteracaoComando(idUsuario, nomeUsuario, sobreNomeUsuario,
										CentralMensagensBrewField.ID_LOGIN, null, usuarioExistente.getCliente()));
					}
				}

				if (msgRetornadaCliente != null
						&& usuarioExistente.getIdComando() == CentralMensagensBrewField.ID_VER_CARRINHO) {
					// primeira vez do usuario no bot paso por aqui
					sendResponse = this.botTelegram.execute(
							new SendPhoto(idUsuario, new java.io.File(CentralMensagensBrewField.FORTE_COPO_GROWLER)));
					logger.info("Foto Enviada:" + sendResponse.isOk() + "Conteudo: " + msgRetornadaCliente);
				}
				if (usuarioExistente.getIdComando() == CentralMensagensBrewField.ID_CONFIRMAR_PEDIDO)
					if (usuarioExistente.getCliente() != null && usuarioExistente.getCliente().getPedido() != null
							&& usuarioExistente.getCliente().getPedido().getPagamento() != null
							&& usuarioExistente.getCliente().getPedido().getPagamento()
									.getFormaPagamento() == CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA
							&& usuarioExistente.isEnviadoFoto()) {
						// enviar dados para celular telegram do jeann
						enviarPedidoCervejariaBrewField(sendResponse, usuarioExistente,
								usuarioExistente.getUrlRecibo());
					} else {
						enviarPedidoCervejariaBrewField(sendResponse, usuarioExistente, null);
					}
				sendResponse = this.botTelegram
						.execute(new SendMessage(idUsuario, msgRetornadaCliente).parseMode(ParseMode.HTML));

				// verificação de mensagem enviada com sucesso
				logger.info("Mensagem Enviada:" + sendResponse.isOk() + "Contteudo: " + msgRetornadaCliente);

			}
		}
	}

	private void enviarPedidoCervejariaBrewField(SendResponse sendResponse, InteracaoComando usuarioExistente,
			String urlRecibo) {
		StringBuilder msg = new StringBuilder();
		try {
			DateFormat dataPadrao = DateFormat.getDateInstance(DateFormat.DEFAULT);
			DateFormat horaPadrao = DateFormat.getTimeInstance(DateFormat.DEFAULT);
			msg.append(CentralMensagensBrewField.DIVISAO_TRACO_INICIO);
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			msg.append(CentralMensagensBrewField.NOME);
			msg.append(usuarioExistente.getNomeUsuario());
			msg.append(CentralMensagensBrewField.ESPACO);
			msg.append(usuarioExistente.getSobreNomeUsuario());
			msg.append(CentralMensagensBrewField.ESPACO);
			msg.append(CentralMensagensBrewField.CPF);
			msg.append(usuarioExistente.getCliente().getCpfCliente());
			msg.append(CentralMensagensBrewField.ESPACO);
			msg.append(CentralMensagensBrewField.CELULAR);
			msg.append(usuarioExistente.getCliente().getTelefone());
			msg.append(CentralMensagensBrewField.DIVISAO);
			msg.append(usuarioExistente.getCliente().getEndereco().getTipo_logradouro());
			msg.append(CentralMensagensBrewField.ESPACO + usuarioExistente.getCliente().getEndereco().getLogradouro());
			msg.append(CentralMensagensBrewField.ESPACO + usuarioExistente.getCliente().getEndereco().getNumero());
			msg.append(CentralMensagensBrewField.ESPACO + usuarioExistente.getCliente().getEndereco().getBairro());
			msg.append(CentralMensagensBrewField.ESPACO + usuarioExistente.getCliente().getEndereco().getCidade());
			msg.append(CentralMensagensBrewField.ESPACO + usuarioExistente.getCliente().getEndereco().getUf());
			msg.append(CentralMensagensBrewField.ESPACO + usuarioExistente.getCliente().getCep());
			msg.append(CentralMensagensBrewField.DIVISAO);
			if (usuarioExistente.getCliente().getPedido() != null) {
				if (usuarioExistente.getCliente().getPedido().getUrlRecibo() != null) {
					msg.append(CentralMensagensBrewField.ESPACO
							+ usuarioExistente.getCliente().getPedido().getUrlRecibo());
				}
				msg.append(CentralMensagensBrewField.TOTAL_DE_PEDIDOS
						+ +usuarioExistente.getCliente().getPedido().getValorTotalPedido());
				msg.append(CentralMensagensBrewField.ESPACO
						+ dataPadrao.format(usuarioExistente.getCliente().getPedido().getDataPedido()));
				msg.append(CentralMensagensBrewField.ESPACO
						+ horaPadrao.format(usuarioExistente.getCliente().getPedido().getDataPedido()));
				msg.append(CentralMensagensBrewField.DIVISAO);
				for (ItemPedidoDTO item : usuarioExistente.getCliente().getPedido().getListaItens()) {
					msg.append(item.getValorCerveja() + CentralMensagensBrewField.ESPACO_TRACO);
					msg.append(CentralMensagensBrewField.ESPACO + item.getEstiloCerveja());
					msg.append(CentralMensagensBrewField.PULAR_LINHA);
				}
				msg.append(CentralMensagensBrewField.DIVISAO);
				String formaPag = "Forma Pagamento: ";
				switch (usuarioExistente.getCliente().getPedido().getPagamento().getFormaPagamento()) {
				case CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA:
					formaPag += CentralMensagensBrewField.TRANSF_BANCARIA;
					break;
				case CentralMensagensBrewField.ID_PICPAY:
					formaPag += CentralMensagensBrewField.PICPAY;
					break;
				case CentralMensagensBrewField.ID_CARTAO_DEBITO:
					formaPag += CentralMensagensBrewField.CARTAO_DEBITO;
					break;
				case CentralMensagensBrewField.ID_CARTAO_CREDITO:
					formaPag += CentralMensagensBrewField.CARTAO_CREDITO;
					break;
				case CentralMensagensBrewField.ID_ANDROID_PAY:
					formaPag += CentralMensagensBrewField.ANDROID_PAY;
					break;
				default:
					break;
				}
				msg.append(formaPag);
				// zera para o clietnerealizar outro pedido
				usuarioExistente.getCliente().setPedido(null);
			} else {
				msg.append("\n ::((( Não recebi os dados do pedido.\n");
			}
		} catch (Exception e) {
			System.out.println("MSG: " + e.toString());
		}
		this.botTelegram.execute(new SendMessage(CentralMensagensBrewField.ID_GRUPO_TELEGRAM, msg.toString()));
		this.botTelegram.execute(new SendMessage(
				CentralMensagensBrewField.DADOS_ENVIADO_A_CERVEJARIA_BREW_FIELD + usuarioExistente.getIdUsuario(),
				msg.toString()));
	}

	private boolean validarComandoRecebido(Message msgTelegram) {
		try {
			Integer.parseInt(msgTelegram.text());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

//	private String processarComandoGeralTelecom(InteracaoComando dadosComando) {
//		CentralComandoTelegramRede obj = new CentralComandoTelegramRede();
//		return obj.processarComando(dadosComando);
//
//	}

//	private String processarComandoGeralFaculdade(InteracaoComando dadosComando) {
//		CentralComandoTelegramFaculdade obj = new CentralComandoTelegramFaculdade();
//		return obj.processarComando(dadosComando);
//
//	}

	private String processarComandoGeralBrewField(InteracaoComando dadosComando) {
		CentralComandoTelegramBrewField obj = new CentralComandoTelegramBrewField();
		return obj.processarComando(dadosComando);
	}
}

//if (usuarioExistente != null) {
//	dadosComando = new InteracaoComando(idUsuario, nomeUsuario, sobreNomeUsuario, idComando,
//			usuarioExistente.getComplementoComando(), usuarioExistente.getCliente());
//	msgRetornadaCliente = processarComandoGeralFaculdade(dadosComando);
//} else {
//	dadosComando = new InteracaoComando(idUsuario, nomeUsuario, sobreNomeUsuario, idComando, null);
//	msgRetornadaCliente = processarComandoGeralBrewField(dadosComando);
//}