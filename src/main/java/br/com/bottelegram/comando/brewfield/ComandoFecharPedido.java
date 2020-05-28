package br.com.bottelegram.comando.brewfield;

import java.util.List;

import org.apache.log4j.Logger;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ItemPedidoDTO;
import br.com.bottelegram.EscopoApplictCSCTimerTelegram;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoFecharPedido {
	private static final Logger logger = Logger.getLogger(ComandoFecharPedido.class);

	public List<ItemPedidoDTO> processarFecharPedido(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();
		msg.append(CentralMensagensBrewField.FECHANDO_SEU_PEDINDO);
 		if (dadosComando.getCliente() != null && dadosComando.getCliente().getPedido() !=null) {
			return dadosComando.getCliente().getPedido().getListaItens();
		}
		return null;
	}

}
