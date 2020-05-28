package br.com.bottelegram.comando.brewfield;

import org.apache.log4j.Logger;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.LoginDTO;
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

	private static final Logger logger = Logger.getLogger(CentralComandoTelegramBrewField.class);

	public static void main(String[] args) {
		CentralComandoTelegramBrewField obj = new CentralComandoTelegramBrewField();
//		obj.processarTesteLogin();
//		obj.processarTesteRealizarPedido();
//		obj.processarTesteFecharPedido();
	}

	// A partir deste método para cada solicitação feita pelo usuário teremos um
	// retorno especifico.
	@Override
	public String processarComando(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();

		switch (dadosComando.getIdComando()) {
		case CentralMensagensBrewField.ID_LOGIN:
			ComandoLogin login = new ComandoLogin();
			if (dadosComando.getComplementoComando() != null && dadosComando.getCliente() != null) {
				LoginDTO res = login.processarLogin(dadosComando);
				msg.append(res.getMensagem());
				if (msg.toString().equalsIgnoreCase(CentralMensagensBrewField.FORMATO_INCORRETO) || !res.isSucesso()) {
					msg.append(refazerlogin(dadosComando));
					dadosComando.setComplementoComando(null);
					return msg.toString();

				} else if (CentralMensagensBrewField.AL_DADOS_CLIENTE
						.equalsIgnoreCase(dadosComando.getComplementoComando())) {
					dadosComando.reiniciarDados();
					msg.append(CentralMensagensBrewField.DADOS_CADASTRAISREMOVIDOS);
					msg.append(login.processarLogin());
					return msg.toString();
				} else if (CentralMensagensBrewField.VL.equalsIgnoreCase(dadosComando.getComplementoComando())) {
					dadosComando.setComplementoComando(null);
				}
				msg.append(iniciarTextoNoLogin(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
				dadosComando.setComplementoComando(null);
			} else {
				// usuario ja cadastrado
				if (dadosComando.getCliente() != null) {
					msg.append(login.processarLogado(dadosComando.getCliente()));

				} else {
					msg.append(login.processarLogin());
				}
			}
			break;
		case CentralMensagensBrewField.ID_ADICIONAR_PEDIDO:
			ComandoRealizarPedido realizPed = new ComandoRealizarPedido();
			if (dadosComando.getComplementoComando() != null) {
				if (dadosComando.getCliente() != null) {
					msg.append(realizPed.processarPedido(dadosComando));
					if (msg.length() > 0) {
						// aqui reapreentar menu de cervejas
						msg.append(
								iniciarTextoEstilos(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
					} else {// escolheu voltar
						/*
						 * Apos voltar ao menu deveria mostrar só uma mensagem: Jeann, confirme seu
						 * pedido ou continue comprando. 2 - Continuar comprando 3 - Selecionar forma de
						 * pagamento 4 - Ver carrinho de compras 5 - Cancelar pedido
						 */
						msg.append(apresentarMenuFecharPedido(dadosComando.getNomeUsuario(),
								dadosComando.getSobreNomeUsuario()));
					}
					// para evitar que se eu digitar 2 ele readicione a mesma cerveja
					dadosComando.setComplementoComando(null);
				} else {
					msg.append(refazerlogin(dadosComando));
				}
			} else {
				if (dadosComando.getCliente() != null) {
					msg.append(realizPed.processarPedido());
					msg.append(iniciarTextoEstilos(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
				} else {
					msg.append(refazerlogin(dadosComando));
				}
			}
			break;
		case CentralMensagensBrewField.ID_VER_CARRINHO:
			ComandoVerCarrinho verCarrinho = new ComandoVerCarrinho();
			String ret = verCarrinho.apresentarCarrinho(dadosComando);
			msg.append(definirMenuProximo(ret, dadosComando));

			break;

		case CentralMensagensBrewField.ID_SELECIONAR_PAGAMENTO:
			ComandoSelecionarPagamento selPagamento = new ComandoSelecionarPagamento();
			msg.append(selPagamento.processarSelecionarPagamento(dadosComando));
			break;

		case CentralMensagensBrewField.ID_CANCELAR_PEDIDO:
			if (dadosComando.getCliente() != null) {
				dadosComando.getCliente().setPedido(null);
				msg.append(iniciarTextoNoLogin(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
			} else {
				msg.append(definirMenuProximo(null, dadosComando));
			}
			break;

		case CentralMensagensBrewField.ID_CONFIRMAR_PEDIDO:
			ComandoConfirmarPedido confPedido = new ComandoConfirmarPedido();
			msg.append(confPedido.processarConfirmarPedido(dadosComando));
			msg.append(confPedido.apresentarCarrinho(dadosComando));
			msg.append(CentralMensagensBrewField.PEDIDO_FINALIZADO);
			msg.append(menuUsuarioCadastrado(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
			break;
		case CentralMensagensBrewField.ID_ALTERAR_DADOS:
			dadosComando.reiniciarDados();
			msg.append(CentralMensagensBrewField.DADOS_CADASTRAISREMOVIDOS);
			login = new ComandoLogin();
			msg.append(login.processarLogin());
			dadosComando.setIdComando(CentralMensagensBrewField.ID_LOGIN);
			break;
		case CentralMensagensBrewField.ID_VOLTAR:
			msg.append(iniciarTexto(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
			break;

		default:
			if (dadosComando.getCliente() != null) {
				msg.append(CentralMensagensBrewField.INFORMAR_JA_CADASTRADO);
				msg.append(menuUsuarioCadastrado(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
			} else {
				if (dadosComando.getMeuContato() != null) {
					ClienteDTO cli = new ClienteDTO(
							dadosComando.getNomeUsuario() + " " + dadosComando.getSobreNomeUsuario(),
							dadosComando.getMeuContato().phoneNumber());
					dadosComando.setCliente(cli);
					dadosComando.setIdComando(CentralMensagensBrewField.ID_LOGIN);
					msg.append(menuSomenteLogin(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
				}
			}

			break;
		}
		return msg.toString();

	}

	private String definirMenuProximo(String ret, InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();

		if (ret != null && ret.length() > 0 && dadosComando.getComplementoComando() == null) {
			msg.append(ret);
		}
		if (dadosComando.getCliente() != null && dadosComando.getCliente().getPedido() != null
				&& dadosComando.getCliente().getPedido().getPagamento() != null) {
			msg.append(menuFechamento(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
		} else {
			if (dadosComando.getCliente() != null && dadosComando.getCliente().getPedido() != null) {
				msg.append(
						apresentarMenuFecharPedido(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
			} else {
				if (dadosComando.getCliente() != null) {
					msg.append(
							menuJaLogadoContinuar(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
				} else {
					msg.append(menuSomenteLogin(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
				}
			}
		}
		return msg.toString();
	}

}