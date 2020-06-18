package br.com.bottelegram.comando.brewfield;

import java.util.Date;

import org.apache.log4j.Logger;

import bancodedados.dto.CartaoFidelidadeDTO;
import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.ItemPedidoDTO;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoConfirmarPedido extends ComandoCarrinho {

	private static final Logger logger = Logger.getLogger(ComandoConfirmarPedido.class);

	public String processarConfirmarPedido(InteracaoComando dadosComando , ClienteDTO clienteTelegram) {
		StringBuilder msg = new StringBuilder();
		if (clienteTelegram != null && dadosComando.getComplementoComando() != null) {
			// validar bonus
//			if ("BN".equalsIgnoreCase(dadosComando.getComplementoComando())) {
//				double menorValor = 0.0;
//				for (ItemPedidoDTO itemPedidoDTO : clienteTelegram.getPedido().getListaItens()) {
//					if (menorValor < itemPedidoDTO.getValorCerveja()) {
//						menorValor = itemPedidoDTO.getValorCerveja();
//					}
//				}
//				clienteTelegram.consumirBonusCartaoFidelidade();
//				msg.append(CentralMensagensBrewField.VALOR_ANTES_DO_BONUS
//						+ clienteTelegram.getPedido().getValorTotalPedido());
//				msg.append(CentralMensagensBrewField.BONUS_CONSUMIDO);
//				double novoValorTotal = clienteTelegram.getPedido().getValorTotalPedido() - menorValor;
//				clienteTelegram.getPedido().setValorTotalPedido(novoValorTotal);
//				msg.append(CentralMensagensBrewField.VALOR_APOS_DO_BONUS
//						+ clienteTelegram.getPedido().getValorTotalPedido());
//				dadosComando.setComplementoComando(null);
//			}
		} else {
			if (clienteTelegram != null) {
				// foto não enviada
				if (!dadosComando.isEnviadoFoto() && clienteTelegram.getPedido() != null
						&& clienteTelegram.getPedido().getPagamento() != null
						&& clienteTelegram.getPedido().getPagamento()
								.getIdPagamento() == CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA) {
					msg.append(CentralMensagensBrewField.AGUARDO_COMPROVANTE);
					return msg.toString();
				}
				if (clienteTelegram.getPedido() != null
						&& clienteTelegram.getPedido().getPagamento() != null
						&& clienteTelegram.getPedido().getPagamento()
								.getIdPagamento() == CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA) {
					msg.append(CentralMensagensBrewField.RECIBO_RECEBIDO_AVALIAR);
					clienteTelegram.getPedido().setUrlRecibo(dadosComando.getUrlRecibo());
					msg.append(CentralMensagensBrewField.AGUARDE_CONFIRMACAO_PAGAMENTO);
				}
//				int bonus = clienteTelegram.consumirBonusCartaoFidelidade();
//				if (bonus > 0) {
//					msg.append(CentralMensagensBrewField.POSSUI_BONUS);
//					msg.append(CentralMensagensBrewField.CONSUMIR_BONUS);
//				}
//				for (ItemPedidoDTO item : clienteTelegram.getPedido().getListaItens()) {
//					CartaoFidelidadeDTO cartaoFid = new CartaoFidelidadeDTO(new Date(),
//							clienteTelegram.getCpfCliente());
//					clienteTelegram.adicionarCartaoFidelidade(cartaoFid);
//				}
				msg.append(CentralMensagensBrewField.DADOS_PEDIDO_REALIZADO);
				msg.append(CentralMensagensBrewField.PULAR_LINHA);
				msg.append(apresentarCarrinho(clienteTelegram));

			} else {
				msg.append(iniciarTexto(dadosComando.getNome()));
			}
		}
		return msg.toString();
	}

}
