package br.com.bottelegram.comando.brewfield;

import java.util.Date;

import org.apache.log4j.Logger;

import bancodedados.dto.CartaoFidelidadeDTO;
import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ItemPedidoDTO;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoConfirmarPedido extends ComandoCarrinho {

	private static final Logger logger = Logger.getLogger(ComandoConfirmarPedido.class);

	public String processarConfirmarPedido(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();
		if (dadosComando.getCliente() != null && dadosComando.getComplementoComando() != null) {
			// validar bonus
			if ("BN".equalsIgnoreCase(dadosComando.getComplementoComando())) {
				double menorValor = 0.0;
				for (ItemPedidoDTO itemPedidoDTO : dadosComando.getCliente().getPedido().getListaItens()) {
					if (menorValor < itemPedidoDTO.getValorCerveja()) {
						menorValor = itemPedidoDTO.getValorCerveja();
					}
				}
				dadosComando.getCliente().consumirBonusCartaoFidelidade();
				msg.append(CentralMensagensBrewField.VALOR_ANTES_DO_BONUS
						+ dadosComando.getCliente().getPedido().getValorTotalPedido());
				msg.append(CentralMensagensBrewField.BONUS_CONSUMIDO);
				double novoValorTotal = dadosComando.getCliente().getPedido().getValorTotalPedido() - menorValor;
				dadosComando.getCliente().getPedido().setValorTotalPedido(novoValorTotal);
				msg.append(CentralMensagensBrewField.VALOR_APOS_DO_BONUS
						+ dadosComando.getCliente().getPedido().getValorTotalPedido());
				dadosComando.setComplementoComando(null);
			}
		} else {
			if (dadosComando.getCliente() != null) {
				// foto não enviada
				if (!dadosComando.isEnviadoFoto() && dadosComando.getCliente().getPedido() != null
						&& dadosComando.getCliente().getPedido().getPagamento() != null
						&& dadosComando.getCliente().getPedido().getPagamento()
								.getFormaPagamento() == CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA) {
					msg.append(CentralMensagensBrewField.AGUARDO_COMPROVANTE);
					return msg.toString();
				}
				if (dadosComando.getCliente().getPedido().getPagamento()
						.getFormaPagamento() == CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA) {
					msg.append(CentralMensagensBrewField.RECIBO_RECEBIDO_AVALIAR);
					dadosComando.getCliente().getPedido().setUrlRecibo(dadosComando.getUrlRecibo());
					msg.append(CentralMensagensBrewField.AGUARDE_CONFIRMACAO_PAGAMENTO);
				}
				int bonus = dadosComando.getCliente().consumirBonusCartaoFidelidade();
				if (bonus > 0) {
					msg.append(CentralMensagensBrewField.POSSUI_BONUS);
					msg.append(CentralMensagensBrewField.CONSUMIR_BONUS);
				}
				for (ItemPedidoDTO item : dadosComando.getCliente().getPedido().getListaItens()) {
					CartaoFidelidadeDTO cartaoFid = new CartaoFidelidadeDTO(new Date(),
							dadosComando.getCliente().getCpfCliente());
					dadosComando.getCliente().adicionarCartaoFidelidade(cartaoFid);
				}
				msg.append(
						CentralMensagensBrewField.TOTAL_DE_PEDIDOS + dadosComando.getCliente().obterCartaoFidelidade());
				msg.append(CentralMensagensBrewField.TOTAL_DE_BONUS
						+ dadosComando.getCliente().obterBonusCartaoFidelidade());
				msg.append(CentralMensagensBrewField.PULAR_LINHA);
				msg.append(CentralMensagensBrewField.DADOS_PEDIDO_REALIZADO);
				msg.append(CentralMensagensBrewField.PULAR_LINHA);
				msg.append(apresentarCarrinho(dadosComando));

			} else {
				msg.append(iniciarTexto(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
			}
		}
		return msg.toString();
	}

}
