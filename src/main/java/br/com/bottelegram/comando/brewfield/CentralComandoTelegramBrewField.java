package br.com.bottelegram.comando.brewfield;

import bancodedados.PostgreSQLJDBCClienteDML;
import bancodedados.PostgreSQLJDBCPedidoDML;

//import org.apache.log4j.Logger;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.EnderecoDTO;
import bancodedados.dto.MenuGrafico;
import br.com.bottelegram.comando.CentralComando;
import br.com.bottelegram.comando.dto.InteracaoComando;

/*
 * Dados do grupo Cerejaria Brew Field
nomeUsuario: null
idUsuario: -376439227
sobreNomeUsuario: null
mensagem: null
Data: Tue May 12 10:53:35 BRT 2020
 */
public class CentralComandoTelegramBrewField extends CentralComando {

//	private static final Logger logger = Logger.getLogger(CentralComandoTelegramBrewField.class);

	public static void main(String[] args) {
		CentralComandoTelegramBrewField obj = new CentralComandoTelegramBrewField();
//		obj.processarTesteLogin();
//		obj.processarTesteRealizarPedido();
//		obj.processarTesteFecharPedido();
	}

	// A partir deste método para cada solicitação feita pelo usuário teremos um
	// retorno especifico.

	public String processarComando(InteracaoComando dadosComando, ClienteDTO clienteTelegram) {
		StringBuilder msg = new StringBuilder();

		System.out.println("dadosComando.getIdComando(): " + dadosComando.getIdComando());
		MenuGrafico menu = new MenuGrafico();

		switch (dadosComando.getIdComando()) {
		case CentralMensagensBrewField.ID_LOGIN:

			if (dadosComando.getComplementoComando() != null) {
				// cadastrar endereco
				if (clienteTelegram != null && clienteTelegram.getEndereco() == null) {
					try {
						EnderecoDTO endereco = new EnderecoDTO(dadosComando.getComplementoComando(), clienteTelegram);
						msg.append(CentralMensagensBrewField.CADASTRADO_COM_SUCESSO);
						msg.append(endereco.toString());
						msg.append(CentralMensagensBrewField.PULAR_LINHA);
						menuJaLogadoContinuar(clienteTelegram);

					} catch (Exception e) {
						System.out.println(e.toString());
						msg.append(CentralMensagensBrewField.INFORMADO_TELEFONE);
					}
				} else {
					if (clienteTelegram != null && clienteTelegram.getEndereco() != null) {

						menuJaLogadoContinuar(clienteTelegram);
					}
				}
			}
			break;
		case CentralMensagensBrewField.ID_ADICIONAR_PEDIDO:
			if (clienteTelegram != null) {
				if (dadosComando.getComplementoComando().contains(CentralMensagensBrewField.ADD)
						|| dadosComando.getComplementoComando().contains(CentralMensagensBrewField.REM)) {
					ComandoRealizarPedido realizPed = new ComandoRealizarPedido();
					String ret = realizPed.processarPedido(dadosComando, clienteTelegram);
					msg.append(ret);
					if (ret == null) {
						// botoe de baixo com os estilos da cerveja.
						iniciarTextoEstilos(clienteTelegram, CentralMensagensBrewField.ADD);
					}
				} else {
					iniciarTextoEstilos(clienteTelegram, CentralMensagensBrewField.ADD);
				}
			}
			break;
		case CentralMensagensBrewField.ID_VER_CARRINHO:
			if (clienteTelegram != null && clienteTelegram.getPedido() != null) {
				ComandoVerCarrinho verCarrinho = new ComandoVerCarrinho();
				String ret = verCarrinho.apresentarCarrinho(dadosComando, clienteTelegram);
				msg.append(ret);
				if (clienteTelegram.getPedido().getListaItens().isEmpty()) {
					menu.zerarBotoesBaixo(dadosComando.getIdUsuarioTelegram());
				} else {
					iniciarTextoEstilos(clienteTelegram, CentralMensagensBrewField.REM);
				}
			} else {
				msg.append(saidaPadrao(clienteTelegram));
			}
			break;

		case CentralMensagensBrewField.ID_SELECIONAR_PAGAMENTO:
			if (clienteTelegram != null && clienteTelegram.getPedido() != null) {
				ComandoSelecionarPagamento selPagamento = new ComandoSelecionarPagamento();
				msg.append(selPagamento.processarSelecionarPagamento(dadosComando, clienteTelegram));
				
			}else {
				msg.append(saidaPadrao(clienteTelegram));
			}

			break;
		case CentralMensagensBrewField.ID_CONFIRMAR_PEDIDO:
			if (clienteTelegram != null && clienteTelegram.getPedido() != null) {
				if (clienteTelegram.getPedido().getPagamento() != null) {
					ComandoConfirmarPedido confPedido = new ComandoConfirmarPedido();
					msg.append(CentralMensagensBrewField.PEDIDO_FINALIZADO);
				} else {
					msg.append(CentralMensagensBrewField.FALTOU_ESCOLHER_PAGAMENTO);
				}
			} else {
				msg.append(saidaPadrao(clienteTelegram));
			}

			break;
		case CentralMensagensBrewField.ID_ALTERAR_DADOS:
			dadosComando.reiniciarDados();
			msg.append(CentralMensagensBrewField.DADOS_CADASTRAISREMOVIDOS);
			PostgreSQLJDBCClienteDML delEnd = new PostgreSQLJDBCClienteDML();
			delEnd.removerEndereco(clienteTelegram.getEndereco());
			msg.append(CentralMensagensBrewField.INFORMADO_TELEFONE);
			dadosComando.setIdComando(CentralMensagensBrewField.ID_LOGIN);
			break;

		default:
			if (clienteTelegram != null && clienteTelegram.getEndereco() != null) {
				msg.append(CentralMensagensBrewField.INFORMAR_CLIENTE_ENDERECO_CADASTRADO);
				menuJaLogadoContinuar(clienteTelegram);
			} else {
				if (dadosComando.getMeuContato() != null && clienteTelegram == null) {
					ClienteDTO cli = new ClienteDTO(dadosComando.getNome() + " " + dadosComando.getSobreNome(),
							dadosComando.getMeuContato().phoneNumber(), dadosComando.getIdUsuarioTelegram());
					dadosComando.setIdComando(CentralMensagensBrewField.ID_LOGIN);
					msg.append(menuSomenteLogin(cli.getNomeCliente()));
					menu.zerarBotoesBaixo(dadosComando.getIdUsuarioTelegram());
				} else {
					if (clienteTelegram != null) {
						msg.append(CentralMensagensBrewField.INFORMADO_TELEFONE);
						dadosComando.setIdComando(CentralMensagensBrewField.ID_LOGIN);
					}
				}
			}
			break;
		}
		return msg.toString();
	}

	private String saidaPadrao(ClienteDTO clienteTelegram) {
		StringBuilder msg = new StringBuilder();
		msg.append(CentralMensagensBrewField.COMECE_A_COMPRAR);
		if (clienteTelegram != null && clienteTelegram.getEndereco() != null) {
			menuJaLogadoContinuar(clienteTelegram);
		}
		return msg.toString();
	}

}