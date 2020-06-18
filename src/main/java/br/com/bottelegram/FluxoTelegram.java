package br.com.bottelegram;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Location;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
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

import bancodedados.PostgreSQLJDBCCartaoDML;
import bancodedados.PostgreSQLJDBCClienteDML;
import bancodedados.PostgreSQLJDBCPagamentoDML;
import bancodedados.PostgreSQLJDBCPedidoDML;
import bancodedados.dto.BonusCartaoFidelidadeDTO;
import bancodedados.dto.CartaoFidelidadeDTO;
import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.ItemPedidoDTO;
import br.com.bottelegram.comando.brewfield.CentralComandoTelegramBrewField;
import br.com.bottelegram.comando.dto.InteracaoComando;

//https://web.telegram.org/#/im?p=@cscpr_bot
public class FluxoTelegram {

//	public static final Logger logger = Logger.getLogger(FluxoTelegram.class);

	protected TelegramBot botTelegram = new TelegramBot(CentralMensagensBrewField.TOKEN_TELEGRAM_FACULDADE);
	private int offSetAtributo = 0;

	public void menuNrTelefone(long idUsuario, String nomeUsuario) {
		KeyboardButton numeroCelular = new KeyboardButton(CentralMensagensBrewField.MEU_CELULAR).requestContact(true);
		KeyboardButton[] vetorBotoes = { numeroCelular };
		Keyboard teclado = new ReplyKeyboardMarkup(vetorBotoes).oneTimeKeyboard(true).resizeKeyboard(true)
				.selective(true);
		SendMessage msgTelegram = new SendMessage(idUsuario,
				nomeUsuario + CentralMensagensBrewField.PRECISO_CADASTRAR_SEU_CELULAR).parseMode(ParseMode.HTML)
						.disableWebPagePreview(false).replyMarkup(teclado);
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
		return CentralMensagensBrewField.RECIBO_INVALIDO_CONTACTAR_BREW_FIELD;
	}

	private ClienteDTO carregarClienteExistente(long idUsuario) {
		PostgreSQLJDBCClienteDML dml = new PostgreSQLJDBCClienteDML();
		ClienteDTO cliente = dml.selecionarClienteByIDTelegram(idUsuario);
		if (cliente != null) {
			PostgreSQLJDBCPedidoDML ped = new PostgreSQLJDBCPedidoDML();
			cliente.setPedido(ped.selecionarPedidoAbertoByTelefone(cliente.getTelefone()));
			if (cliente.getPedido() != null) {
				cliente.getPedido().setListaItens(ped.selecionarItemPedidoAbertoByTelefone(cliente.getPedido()));
				PostgreSQLJDBCPagamentoDML pag = new PostgreSQLJDBCPagamentoDML();
				cliente.getPedido().setPagamento(pag.selecionarPagamentoPedidoAbertoByTelefone(cliente.getPedido()));
			}
			PostgreSQLJDBCCartaoDML cartao = new PostgreSQLJDBCCartaoDML();
			cliente.setBonus(cartao.selecionarBonusNoConsumidoNoAtivado(cliente));
		}
		return cliente;
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

		// an√°lise de cada a√ß√£o da mensagem
		if (updates != null) {
			for (Update update : updates) {
				InteracaoComando dadosEntrada = validarComandoRecebido(update);
				if (dadosEntrada == null) {
					this.botTelegram.execute(new SendMessage(update.message().chat().id(), ""));
					updatesResponse.updates().clear();
					return;
				}
				System.out.println(CentralMensagensBrewField.DIVISAO_TRACO_INICIO);
				System.out.println(dadosEntrada);
				System.out.println(CentralMensagensBrewField.DIVISAO_TRACO);
				this.offSetAtributo = update.updateId() + 1;
				// envio de "Escrevendo" antes de enviar a resposta
				baseResponse = this.botTelegram
						.execute(new SendChatAction(dadosEntrada.getIdUsuarioTelegram(), ChatAction.typing.name()));
				System.out.println("Escrevendo ..." + baseResponse.isOk());
				// verifica√ß√£o de a√ß√£o de chat foi enviada com sucesso
				System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

//				MenuGrafico menu = new MenuGrafico();
//				menuNrTelefone(dadosEntrada.getIdUsuarioTelegram() , "Douglas");
//				menu.botoesFormasPAgamento(dadosEntrada.getIdUsuarioTelegram());
//				menu.zerarBotoesBaixo(dadosEntrada.getIdUsuarioTelegram());

				// envio da mensagem de resposta
				String msgRetornadaCliente = "";
				ClienteDTO clienteTelegram = carregarClienteExistente(dadosEntrada.getIdUsuarioTelegram());
				if (clienteTelegram != null) {
					System.out.println("Usuario existe e com celular-contato: ");
					if (clienteTelegram.getEndereco() != null) {

						msgRetornadaCliente = processarComandoGeralBrewField(dadosEntrada, clienteTelegram);
						atualizarMapa(dadosEntrada);

						if (msgRetornadaCliente != null && clienteTelegram.getPedido() != null
								&& dadosEntrada.getIdComando() == CentralMensagensBrewField.ID_VER_CARRINHO) {
							// primeira vez do usuario no bot paso por aqui
							sendResponse = this.botTelegram.execute(new SendPhoto(dadosEntrada.getIdUsuarioTelegram(),
									new java.io.File(CentralMensagensBrewField.FORTE_COPO_GROWLER)));
							System.out.println(
									"Foto Enviada:" + sendResponse.isOk() + "Conteudo: " + msgRetornadaCliente);
						}
						if (dadosEntrada.getIdComando() == CentralMensagensBrewField.ID_CONFIRMAR_PEDIDO
								&& clienteTelegram.getPedido() != null
								&& clienteTelegram.getPedido().getPagamento() != null) {
							if (clienteTelegram.getPedido().getPagamento()
									.getIdPagamento() == CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA
									&& dadosEntrada.isEnviadoFoto()) {
								// enviar dados para celular telegram do jeann
								enviarPedidoCervejariaBrewField(clienteTelegram, dadosEntrada.getUrlRecibo());
							} else {
								enviarPedidoCervejariaBrewField(clienteTelegram, null);
								this.botTelegram.execute(new SendPhoto(dadosEntrada.getIdUsuarioTelegram(),
										new java.io.File(CentralMensagensBrewField.JOIA)));

							}

						}
					} else {
						dadosEntrada.setIdComando(CentralMensagensBrewField.ID_LOGIN);
						msgRetornadaCliente = processarComandoGeralBrewField(dadosEntrada, clienteTelegram);
						atualizarMapa(dadosEntrada);
					}
				} else {
					if (dadosEntrada.getMeuContato() == null) {
						menuNrTelefone(dadosEntrada.getIdUsuarioTelegram(), dadosEntrada.getNome());
					}
					msgRetornadaCliente = processarComandoGeralBrewField(dadosEntrada, clienteTelegram);

				}
				sendResponse = this.botTelegram
						.execute(new SendMessage(dadosEntrada.getIdUsuarioTelegram(), msgRetornadaCliente)
								.parseMode(ParseMode.HTML));
				// verifica√ß√£o de mensagem enviada com sucesso
				System.out.println("Mensagem Enviada:" + sendResponse.isOk() + "Contteudo: " + msgRetornadaCliente);
			}
		}
	}

	private void atualizarMapa(InteracaoComando dadosEntrada) {
		InteracaoComando dadosMapaTemp = EscopoApplictCSCTimerTelegram.mapaClienteComando
				.get(dadosEntrada.getIdUsuarioTelegram());
		dadosMapaTemp.setIdComando(dadosEntrada.getIdComando());
	}

	public static int LIMITE_BRINDE = 10;

	private void enviarPedidoCervejariaBrewField(ClienteDTO cliente, String urlRecibo) {
		StringBuilder msg = new StringBuilder();
		try {
			DateFormat dataPadrao = DateFormat.getDateInstance(DateFormat.DEFAULT);
			DateFormat horaPadrao = DateFormat.getTimeInstance(DateFormat.DEFAULT);
			msg.append(CentralMensagensBrewField.DIVISAO_TRACO_INICIO);
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			msg.append(CentralMensagensBrewField.ESPACO + dataPadrao.format(new Date()));
			msg.append(CentralMensagensBrewField.ESPACO + horaPadrao.format(new Date()));
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			msg.append(CentralMensagensBrewField.NOME);
			msg.append(cliente.getNomeCliente());
			msg.append(CentralMensagensBrewField.ESPACO);
			if (CentralMensagensBrewField.CPF_PADRAO != cliente.getCpfCliente()) {
				msg.append(CentralMensagensBrewField.CPF);
				msg.append(cliente.getCpfCliente());
				msg.append(CentralMensagensBrewField.ESPACO);
			}
			msg.append(CentralMensagensBrewField.CELULAR);
			msg.append(cliente.getTelefone());
			msg.append(CentralMensagensBrewField.DIVISAO);
			msg.append(cliente.getEndereco().getTipo_logradouro());
			msg.append(CentralMensagensBrewField.ESPACO + cliente.getEndereco().getLogradouro());
			msg.append(CentralMensagensBrewField.ESPACO + cliente.getEndereco().getNumero());
			msg.append(CentralMensagensBrewField.ESPACO + cliente.getEndereco().getBairro());
			msg.append(CentralMensagensBrewField.ESPACO + cliente.getEndereco().getCidade());
			msg.append(CentralMensagensBrewField.ESPACO + cliente.getEndereco().getUf());
			msg.append(CentralMensagensBrewField.ESPACO + cliente.getEndereco().getCep());
			msg.append(CentralMensagensBrewField.DIVISAO);
			if (cliente.getPedido() != null) {
				if (cliente.getPedido().getUrlRecibo() != null) {
					msg.append(CentralMensagensBrewField.ESPACO + cliente.getPedido().getUrlRecibo());
				}
				msg.append(CentralMensagensBrewField.TOTAL_DE_PEDIDOS + +cliente.getPedido().getValorTotalPedido());

				msg.append(CentralMensagensBrewField.DIVISAO);
				for (ItemPedidoDTO item : cliente.getPedido().getListaItens()) {
					msg.append(item.getValorCerveja() + CentralMensagensBrewField.ESPACO_TRACO);
					msg.append(CentralMensagensBrewField.ESPACO + item.getEstiloCerveja());
					msg.append(CentralMensagensBrewField.PULAR_LINHA);
				}
				msg.append(CentralMensagensBrewField.DIVISAO);
				String formaPag = "Forma Pagamento: " + cliente.getPedido().getPagamento().getDescPagamento();
				msg.append(formaPag);
				msg.append(CentralMensagensBrewField.PULAR_LINHA);
				msg.append(processarSelosPremios(cliente));

			} else {
				msg.append("\n ::((( N„o recebi os dados do pedido.\n");
			}
		} catch (Exception e) {
			System.out.println("MSG: " + e.toString());
		}
		this.botTelegram.execute(
				new SendMessage(CentralMensagensBrewField.ID_GRUPO_TELEGRAM, msg.toString()).parseMode(ParseMode.HTML));
		this.botTelegram.execute(new SendMessage(
				CentralMensagensBrewField.DADOS_ENVIADO_A_CERVEJARIA_BREW_FIELD + cliente.getIdUsuarioTelegram(),
				msg.toString()).parseMode(ParseMode.HTML));
	}

	private String processarSelosPremios(ClienteDTO cliente) {
		StringBuffer msg = new StringBuffer();
		// cadastrar bonus e seleo
		PostgreSQLJDBCCartaoDML cartaoDML = new PostgreSQLJDBCCartaoDML();

		if (cliente.getBonus() == null) {
			cartaoDML.cadastrarBonus(cliente);
		}
		BonusCartaoFidelidadeDTO bonus = cartaoDML.selecionarBonusNoConsumidoNoAtivado(cliente);
		List<CartaoFidelidadeDTO> listaSelos = cartaoDML.selecionarCartaoFidelidade(bonus, cliente.getPedido());
		int qdadeByBonus = listaSelos.size();
		msg.append("\nSelos acumulados ANTES:" + qdadeByBonus);
		for (int i = 0; i < cliente.getPedido().getListaItens().size(); i++) {
			if (qdadeByBonus >= LIMITE_BRINDE) {
				cartaoDML.desativarBonusPromoverPremiacao(bonus);
				cartaoDML.cadastrarBonus(cliente);// cadastrara um novo
				bonus = cartaoDML.selecionarBonusNoConsumidoNoAtivado(cliente);
				msg.append("\nCliente possui BÙnus para premiaÁ„o.");
			}
			cartaoDML.cadastrarItemCartao(bonus, cliente.getPedido());
			qdadeByBonus++;
		}
		msg.append("\nSelos acumulados DEPOIS:" + qdadeByBonus);
		return msg.toString();
	}

	private InteracaoComando validarComandoRecebido(Update update) {
		try {
			String complemento;
			int opcao = 0;
			Message msgTelegram = update.message();
			if (msgTelegram == null) {
				msgTelegram = update.callbackQuery().message();
				complemento = update.callbackQuery().data();
				opcao = Integer.parseInt(update.callbackQuery().data());
			} else {
				complemento = update.message().text();
				try {
					opcao = Integer.parseInt(msgTelegram.text());
				} catch (Exception e) {
					System.out.println("OpÁ„o difere de digito.");
				}
			}
			InteracaoComando dadosEntrada = EscopoApplictCSCTimerTelegram.mapaClienteComando
					.get(msgTelegram.chat().id());
			try {
				Location localidade = msgTelegram.location();
				if (dadosEntrada == null) {
					dadosEntrada = new InteracaoComando();
					dadosEntrada.setIdComando(0);
				} else {
					if (opcao == 0 && dadosEntrada.getIdComando() != 0) {
						opcao = dadosEntrada.getIdComando();
					}
				}
				dadosEntrada.setIdUsuarioTelegram(msgTelegram.chat().id());
				dadosEntrada.setNome(msgTelegram.chat().firstName());
				dadosEntrada.setMeuContato(msgTelegram.contact());
				dadosEntrada.setComplementoComando(complemento);

				boolean opInValida = true;
				for (int i = 0; i < CentralMensagensBrewField.vetMenuPrincipalOpcoesValidas.length; i++) {
					if (opcao == CentralMensagensBrewField.vetMenuPrincipalOpcoesValidas[i]) {
						dadosEntrada.setIdComando(opcao);
						opInValida = false;
						break;
					}
				}
				if (opInValida) {
					dadosEntrada.setIdComando(0);
				}
			} catch (Exception e) {
				System.out.println("Mensagem recebida difere de comando: " + msgTelegram.text());
			}
			if (msgTelegram.photo() != null) {
				PhotoSize f[] = msgTelegram.photo();
				dadosEntrada.setEnviadoFoto(true);
				System.out.println("Foto Recebida");
				dadosEntrada.setUrlRecibo(getFileUrl(f[0].fileId()));
			}
			EscopoApplictCSCTimerTelegram.mapaClienteComando.put(dadosEntrada.getIdUsuarioTelegram(),
					new InteracaoComando(dadosEntrada.getIdComando(), dadosEntrada.getComplementoComando(),
							dadosEntrada.getNome(), dadosEntrada.getIdUsuarioTelegram(), dadosEntrada.getSobreNome()));
			return dadosEntrada;
		} catch (Exception e) {
			System.out.println("OpÁ„o invalida ou Botao opcoes sem valor.");
		}
		return null;
	}

	private String processarComandoGeralBrewField(InteracaoComando dadosComando, ClienteDTO clienteDTO) {
		CentralComandoTelegramBrewField obj = new CentralComandoTelegramBrewField();
		return obj.processarComando(dadosComando, clienteDTO);
	}
}
