package br.com.bottelegram.comando.brewfield;

import java.util.List;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ItemPedidoDTO;
import br.com.bottelegram.comando.CentralComando;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoCarrinho extends CentralComando {

	protected String apresentarCarrinhoResumido(InteracaoComando dadosComando) {
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
			msg.append(CentralMensagensBrewField.TOTAL_DE_PEDIDOS + totalCompra );
		}
		return msg.toString();
	}

	protected String apresentarCarrinho(InteracaoComando dadosComando) {
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

			msg.append(CentralMensagensBrewField.BLZ + dadosComando.getNomeUsuario());
			// verificar se o valor não superou 100 reais.

			msg.append(CentralMensagensBrewField.VALOR_DO_PEDIDO_RS + totalCompra);
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			msg.append(CentralMensagensBrewField.CELULAR + dadosComando.getCliente().getTelefone());
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			msg.append(CentralMensagensBrewField.ENDERECO_DE_ENTREGA + dadosComando.getCliente().getEndereco());

			if (dadosComando.getCliente().getPedido() != null
					&& dadosComando.getCliente().getPedido().getUrlRecibo() != null
					&& dadosComando.getCliente().getPedido().getUrlRecibo().length() > 0) {
				msg.append(CentralMensagensBrewField.URL_RECIBO + dadosComando.getCliente().getPedido().getUrlRecibo());
			}
		}
		return msg.toString();
	}

	@Override
	protected String processarComando(InteracaoComando dadosComando) {
		return null;
	}

}
