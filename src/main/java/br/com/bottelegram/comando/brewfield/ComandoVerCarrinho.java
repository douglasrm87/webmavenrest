package br.com.bottelegram.comando.brewfield;

import org.apache.log4j.Logger;

import bancodedados.dto.CentralMensagensBrewField;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoVerCarrinho extends ComandoCarrinho{
	private static final Logger logger = Logger.getLogger(ComandoVerCarrinho.class);

	public String processarVerCarrinho(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();
		if (dadosComando.getCliente() != null && dadosComando.getCliente().getPedido() != null) {

			msg.append(apresentarCarrinho(dadosComando));
			if (CentralMensagensBrewField.VL.equalsIgnoreCase(dadosComando.getComplementoComando())) {
				msg.append(apresentarMenuFecharPedido(dadosComando.getNomeUsuario(),
						dadosComando.getSobreNomeUsuario()));
				dadosComando.setComplementoComando(null);
				return msg.toString();
			}
		} else {
			if (dadosComando.getCliente() != null) {
				msg.append(jaLogadoContinuar(dadosComando));
			} else {
				msg.append(refazerlogin(dadosComando));
			}
			return msg.toString();
		}
		msg.append(apresentarMenuFecharPedido(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
		
		return msg.toString();
	}

}
