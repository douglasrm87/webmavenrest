package br.com.bottelegram.comando.brewfield;

import org.apache.log4j.Logger;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoSelecionarPagamento extends ComandoCarrinho {

	private static final Logger logger = Logger.getLogger(ComandoSelecionarPagamento.class);

	public String processarSelecionarPagamento(InteracaoComando dadosComando, ClienteDTO clienteTelegram) {
		StringBuilder msg = new StringBuilder();
		// validar de tem pelo menos 2 pedidos
		if (clienteTelegram != null && clienteTelegram.getPedido() != null && clienteTelegram.getPedido()
				.getListaItens().size() >= CentralMensagensBrewField.NUMERO_MINIMO_GROWLER) {
			if (dadosComando.getComplementoComando()
					.equals(String.valueOf(CentralMensagensBrewField.ID_SELECIONAR_PAGAMENTO))) {
				menuPagamento(clienteTelegram);
			}

			ComandoPagamento pagamento = new ComandoPagamento();
			msg.append(pagamento.processarPagamento(dadosComando, clienteTelegram));
			if (clienteTelegram.getPedido() != null && clienteTelegram.getPedido().getPagamento() != null
					&& clienteTelegram.getPedido().getPagamento()
							.getIdPagamento() != CentralMensagensBrewField.VALOR_INICIAL_FORMA_PAG) {
				msg.append(calcularTaxa(clienteTelegram));
				msg.append(CentralMensagensBrewField.MSG_FECHAMENTO);
				msg.append(CentralMensagensBrewField.AGORA_SO_FALTA_CONFIRMAR_SEU_PEDIDO);
				if (clienteTelegram.getPedido().getPagamento()
						.getIdPagamento() == CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA) {
					msg.append(CentralMensagensBrewField.ENVIAR_SEU_COMPROVANTE_PARA_MIM);
				}

				return msg.toString();
			}

			dadosComando.setComplementoComando(null);
		} else {
			msg.append(CentralMensagensBrewField.MSG_NUMERO_MINIMO_GROWLER);
		}

		return msg.toString();
	}

}
