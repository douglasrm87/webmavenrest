package br.com.bottelegram.comando.brewfield;

import org.apache.log4j.Logger;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ItemPedidoDTO;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoSelecionarPagamento extends ComandoCarrinho {

	private static final Logger logger = Logger.getLogger(ComandoSelecionarPagamento.class);

	public String processarSelecionarPagamento(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();
		// validar de tem pelo menos 2 pedidos
		if (dadosComando.getCliente() != null && dadosComando.getCliente().getPedido() != null && dadosComando
				.getCliente().getPedido().getListaItens().size() >= CentralMensagensBrewField.NUMERO_MINIMO_GROWLER) {
			msg.append(CentralMensagensBrewField.OPCOES_PAGAMENTO_DISPONIVEIS);
			msg.append(menuPagamento(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
			if (dadosComando.getComplementoComando() != null) {
				if (CentralMensagensBrewField.VL.equalsIgnoreCase(dadosComando.getComplementoComando())) {
					msg.append(apresentarMenuFecharPedido(dadosComando.getNomeUsuario(),
							dadosComando.getSobreNomeUsuario()));
					dadosComando.setComplementoComando(null);
					return msg.toString();
				} else {
					ComandoPagamento pagamento = new ComandoPagamento();
					msg.append(pagamento.processarPagamento(dadosComando));
					msg.append(CentralMensagensBrewField.PULAR_LINHA);
					if (dadosComando.getCliente().getPedido() != null
							&& dadosComando.getCliente().getPedido().getPagamento() != null
							&& dadosComando.getCliente().getPedido().getPagamento()
									.getFormaPagamento() != CentralMensagensBrewField.VALOR_INICIAL_FORMA_PAG) {
						if (dadosComando.getCliente().getPedido()
								.getValorTotalPedido() < CentralMensagensBrewField.LIMIAR_TAXA_ENTREGA) {
							msg.append(CentralMensagensBrewField.MSG_TAXA_ENTREGA);
							msg.append(CentralMensagensBrewField.PULAR_LINHA);
						}
						double valorTotal = 0.0;
						boolean addTaxa = true;
						ItemPedidoDTO itemTaxaRemover = null;
						for (ItemPedidoDTO item : dadosComando.getCliente().getPedido().getListaItens()) {
							// evitara a 2 taxas
							if (item.getEstiloCerveja().equalsIgnoreCase(CentralMensagensBrewField.MSG_TAXA)) {
								addTaxa = false;
								valorTotal -= CentralMensagensBrewField.TAXA_ENTREGA_MENOR_100;
								itemTaxaRemover = item;
							}
							valorTotal += item.getValorCerveja();
						}
						if (itemTaxaRemover != null && valorTotal >= CentralMensagensBrewField.LIMIAR_TAXA_ENTREGA) {
							dadosComando.getCliente().getPedido().getListaItens().remove(itemTaxaRemover);
						} else {
							if (valorTotal < CentralMensagensBrewField.LIMIAR_TAXA_ENTREGA) {
								// volta taxa
								valorTotal += CentralMensagensBrewField.TAXA_ENTREGA_MENOR_100;
							}
						}
						dadosComando.getCliente().getPedido().setValorTotalPedido(valorTotal);
						if (valorTotal < CentralMensagensBrewField.LIMIAR_TAXA_ENTREGA) {
							if (addTaxa) {
								dadosComando.getCliente().getPedido().getListaItens()
										.add(new ItemPedidoDTO(CentralMensagensBrewField.MSG_TAXA,
												CentralMensagensBrewField.TAXA_ENTREGA_MENOR_100));
							}
						} else {
							msg.append(CentralMensagensBrewField.SEM_TAXA_ENTREGA);
						}
//						msg.append(apresentarCarrinho(dadosComando));
						msg.append(CentralMensagensBrewField.MSG_FECHAMENTO);
						msg.append(CentralMensagensBrewField.AGORA_SO_FALTA_CONFIRMAR_SEU_PEDIDO);
						msg.append(CentralMensagensBrewField.ID_CONFIRMAR_PEDIDO);
						if (dadosComando.getCliente().getPedido().getPagamento()
								.getFormaPagamento() == CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA) {
							msg.append(CentralMensagensBrewField.ENVIAR_SEU_COMPROVANTE_PARA_MIM);
						}
						msg.append(CentralMensagensBrewField.PULAR_LINHA);
						dadosComando.setComplementoComando(null);
						msg.append(menuFechamento(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
						return msg.toString();
					}
				}
			}
			dadosComando.setComplementoComando(null);
		} else {
			msg.append(CentralMensagensBrewField.MSG_NUMERO_MINIMO_GROWLER);
			msg.append(iniciarTextoNoLogin(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
		}

		return msg.toString();
	}

}
