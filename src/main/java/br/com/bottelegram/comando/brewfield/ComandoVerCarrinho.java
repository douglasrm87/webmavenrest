package br.com.bottelegram.comando.brewfield;

import bancodedados.dto.ClienteDTO;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoVerCarrinho extends ComandoCarrinho {
//	private static final Logger logger = Logger.getLogger(ComandoVerCarrinho.class);

	public String processarVerCarrinho(InteracaoComando dadosComando, ClienteDTO clienteTelegram) {
		StringBuilder msg = new StringBuilder();
		if (clienteTelegram != null && clienteTelegram.getPedido() != null) {

			msg.append(apresentarCarrinho(dadosComando, clienteTelegram));

		} else {
			if (clienteTelegram != null) {
				msg.append(jaLogadoContinuar(dadosComando));
			} else {
				msg.append(refazerlogin(dadosComando));
			}
			return msg.toString();
		}
		msg.append(apresentarMenuFecharPedido(clienteTelegram.getNomeCliente()));

		return msg.toString();
	}

}
