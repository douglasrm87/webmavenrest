package br.com.bottelegram.comando.brewfield;

import java.util.List;

import org.apache.log4j.Logger;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.ItemPedidoDTO;

public class ComandoFecharPedido {
	private static final Logger logger = Logger.getLogger(ComandoFecharPedido.class);

	public List<ItemPedidoDTO> processarFecharPedido(ClienteDTO dadosComando) {
		StringBuilder msg = new StringBuilder();
		msg.append(CentralMensagensBrewField.FECHANDO_SEU_PEDINDO);
 		if (dadosComando != null && dadosComando.getPedido() !=null) {
			return dadosComando.getPedido().getListaItens();
		}
		return null;
	}

}
