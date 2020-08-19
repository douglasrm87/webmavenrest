package br.com.bottelegram.comando.brewfield;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoConfirmarPedido extends ComandoCarrinho {

//	private static final Logger logger = Logger.getLogger(ComandoConfirmarPedido.class);

	public String processarConfirmarPedido(InteracaoComando dadosComando, ClienteDTO clienteTelegram) {
		StringBuilder msg = new StringBuilder();

		if (clienteTelegram != null) {
			// foto não enviada
			if (!dadosComando.isEnviadoFoto() && clienteTelegram.getPedido() != null
					&& clienteTelegram.getPedido().getPagamento() != null && clienteTelegram.getPedido().getPagamento()
							.getIdPagamento() == CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA) {
				msg.append(CentralMensagensBrewField.AGUARDO_COMPROVANTE);
				return msg.toString();
			}
			if (clienteTelegram.getPedido() != null && clienteTelegram.getPedido().getPagamento() != null
					&& clienteTelegram.getPedido().getPagamento()
							.getIdPagamento() == CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA) {
				msg.append(CentralMensagensBrewField.RECIBO_RECEBIDO_AVALIAR);
				clienteTelegram.getPedido().setUrlRecibo(dadosComando.getUrlRecibo());
				msg.append(CentralMensagensBrewField.AGUARDE_CONFIRMACAO_PAGAMENTO);
			}
			msg.append(CentralMensagensBrewField.DADOS_PEDIDO_REALIZADO);
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			msg.append(apresentarCarrinho(dadosComando, clienteTelegram));

		} else {
			msg.append(iniciarTexto(dadosComando.getNome()));
		}

		return msg.toString();
	}

}
