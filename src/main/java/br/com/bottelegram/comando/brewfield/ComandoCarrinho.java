package br.com.bottelegram.comando.brewfield;

import java.util.List;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.ItemPedidoDTO;
import br.com.bottelegram.comando.CentralComando;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoCarrinho extends CentralComando {

	protected String apresentarCarrinhoResumido(ClienteDTO dadosComando) {
		StringBuilder msg = new StringBuilder();
		ComandoFecharPedido fecharPed = new ComandoFecharPedido();
		List<ItemPedidoDTO> listaitens = fecharPed.processarFecharPedido(dadosComando);
		if (listaitens != null) {
			double totalCompra = 0.0;
			msg.append(CentralMensagensBrewField.ITENS_DO_CARRINHO);
			msg.append(CentralMensagensBrewField.QUANTIDADE_ESCOLHIDA + listaitens.size());
			msg.append(CentralMensagensBrewField.PULAR_LINHA);

			for (ItemPedidoDTO itemPedidoDTO : listaitens) {
				msg.append(itemPedidoDTO.getEstiloCerveja() + " - R$ " + itemPedidoDTO.getValorCerveja() + "\n");
				totalCompra += itemPedidoDTO.getValorCerveja();
			}
			msg.append(CentralMensagensBrewField.TOTAL_DE_PEDIDOS + totalCompra);
		}
		return msg.toString();
	}

	protected String apresentarCarrinho(ClienteDTO clienteTelegram) {
		StringBuilder msg = new StringBuilder();
		ComandoFecharPedido fecharPed = new ComandoFecharPedido();
		msg.append(calcularTaxa(clienteTelegram));
		List<ItemPedidoDTO> listaitens = fecharPed.processarFecharPedido(clienteTelegram);
		if (listaitens != null) {
			double totalCompra = 0.0;
			int qdadeitens = 0;
			for (ItemPedidoDTO itemPedidoDTO : listaitens) {
				if (!CentralMensagensBrewField.MSG_TAXA.equalsIgnoreCase(itemPedidoDTO.getEstiloCerveja())) {
					qdadeitens++;
				}
			}
			msg.append(CentralMensagensBrewField.QUANTIDADE_ESCOLHIDA + qdadeitens);
			msg.append(CentralMensagensBrewField.PULAR_LINHA);

			for (ItemPedidoDTO itemPedidoDTO : listaitens) {
				msg.append(itemPedidoDTO.getEstiloCerveja() + " - R$ " + itemPedidoDTO.getValorCerveja() + "\n");
				totalCompra += itemPedidoDTO.getValorCerveja();
			}

			msg.append(CentralMensagensBrewField.BLZ + clienteTelegram.getNomeCliente());
			// verificar se o valor não superou 100 reais.

			if (totalCompra < CentralMensagensBrewField.LIMIAR_TAXA_ENTREGA) {
				msg.append(CentralMensagensBrewField.VALOR_DO_PEDIDO_RS + totalCompra + " "
						+ CentralMensagensBrewField.MSG_TAXA + CentralMensagensBrewField.TAXA_ENTREGA_MENOR_100
						+ " = R$ " + (totalCompra += CentralMensagensBrewField.TAXA_ENTREGA_MENOR_100));
				;
			} else {
				msg.append(CentralMensagensBrewField.VALOR_DO_PEDIDO_RS + totalCompra);
			}
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			msg.append(CentralMensagensBrewField.CELULAR + clienteTelegram.getTelefone());
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			msg.append(CentralMensagensBrewField.ENDERECO_DE_ENTREGA + clienteTelegram.getEndereco());
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			if (clienteTelegram.getPedido() != null && clienteTelegram.getPedido().getPagamento() != null) {
				msg.append(CentralMensagensBrewField.PAGAMENTO_ESCOLHIDO + clienteTelegram.getPedido().getPagamento());
			}
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			if (clienteTelegram.getPedido() != null && clienteTelegram.getPedido().getUrlRecibo() != null
					&& clienteTelegram.getPedido().getUrlRecibo().length() > 0) {
				msg.append(CentralMensagensBrewField.URL_RECIBO + clienteTelegram.getPedido().getUrlRecibo());
			}
		}
		return msg.toString();
	}

	protected String calcularTaxa(ClienteDTO dadosComando) {
		StringBuilder msg = new StringBuilder();

		if (dadosComando.getPedido().getValorTotalPedido() < CentralMensagensBrewField.LIMIAR_TAXA_ENTREGA) {
			msg.append(CentralMensagensBrewField.MSG_TAXA_ENTREGA);
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
		}
		double valorTotal = 0.0;
		boolean addTaxa = true;
		ItemPedidoDTO itemTaxaRemover = null;
		for (ItemPedidoDTO item : dadosComando.getPedido().getListaItens()) {
			// evitara a 2 taxas
			if (item.getEstiloCerveja().equalsIgnoreCase(CentralMensagensBrewField.MSG_TAXA)) {
				addTaxa = false;
				valorTotal -= CentralMensagensBrewField.TAXA_ENTREGA_MENOR_100;
				itemTaxaRemover = item;
			}
			valorTotal += item.getValorCerveja();
		}
		if (itemTaxaRemover != null && valorTotal >= CentralMensagensBrewField.LIMIAR_TAXA_ENTREGA) {
			dadosComando.getPedido().getListaItens().remove(itemTaxaRemover);
		} else {
			if (valorTotal < CentralMensagensBrewField.LIMIAR_TAXA_ENTREGA) {
				// volta taxa
				valorTotal += CentralMensagensBrewField.TAXA_ENTREGA_MENOR_100;
			}
		}
		dadosComando.getPedido().setValorTotalPedido(valorTotal);
		if (valorTotal < CentralMensagensBrewField.LIMIAR_TAXA_ENTREGA) {
			if (addTaxa) {
//				dadosComando.getPedido().getListaItens().add(new ItemPedidoDTO(CentralMensagensBrewField.MSG_TAXA,
//						CentralMensagensBrewField.TAXA_ENTREGA_MENOR_100));
			}
		} else {
			msg.append(CentralMensagensBrewField.SEM_TAXA_ENTREGA);
		}

		return msg.toString();
	}
}
