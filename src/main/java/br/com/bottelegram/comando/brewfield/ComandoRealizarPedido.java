package br.com.bottelegram.comando.brewfield;

import java.text.DateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ItemPedidoDTO;
import bancodedados.dto.PedidoDTO;
import br.com.bottelegram.EscopoApplictCSCTimerTelegram;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoRealizarPedido {

	
	private static final Logger logger = Logger.getLogger(ComandoRealizarPedido.class);

	public String processarPedido() {
		StringBuilder msg = new StringBuilder();
		msg.append(CentralMensagensBrewField.ESCOLHA_UM_DOS_ESTILOS_DISPONIVEIS);
		return msg.toString();
	}

	public String processarPedido(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();

		double valor = 0.0;
		String estiloEscolhido = new String();
		boolean estiloValido = false;
		if (CentralMensagensBrewField.GG.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			valor = CentralMensagensBrewField.VALOR_PILSEN;
			estiloEscolhido = CentralMensagensBrewField.GELA_GOELA;
			estiloValido = true;
		}
		if (CentralMensagensBrewField.RD.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			valor = CentralMensagensBrewField.VALOR_RED;
			estiloEscolhido = CentralMensagensBrewField.RED_ALE;
			estiloValido = true;
		}
		if (CentralMensagensBrewField.AP.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			valor = CentralMensagensBrewField.VALOR_APA;
			estiloEscolhido = CentralMensagensBrewField.BREAK_DOWN_APA;
			estiloValido = true;
		}
		if (CentralMensagensBrewField.PT.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			valor = CentralMensagensBrewField.VALOR_PORTER;
			estiloEscolhido = CentralMensagensBrewField.BLACK_SAILS;
			estiloValido = true;
		}
		if (CentralMensagensBrewField.IP.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			valor = CentralMensagensBrewField.VALOR_IPA;
			estiloEscolhido = CentralMensagensBrewField.BREAK_DOWN_IPA;
			estiloValido = true;
		}
		if (CentralMensagensBrewField.VL.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			dadosComando.setComplementoComando(null);
			return msg.toString();
		}
		if (estiloValido) {
			msg.append(processarPedido(valor, estiloEscolhido, dadosComando));
		}else {
			msg.append(CentralMensagensBrewField.ITEM_NAO_CADASTRADO);
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
		}
		return msg.toString();
	}

	private String processarPedido(double valor, String estiloEscolhido, InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();
		DateFormat dataPadrao = DateFormat.getDateInstance(DateFormat.DEFAULT);
		DateFormat horaPadrao = DateFormat.getTimeInstance(DateFormat.DEFAULT);

		InteracaoComando usuarioExistente = EscopoApplictCSCTimerTelegram.mapaClienteComando
				.get(dadosComando.getIdUsuario());
		if (dadosComando.getCliente().getPedido() == null) {
			PedidoDTO pedido = new PedidoDTO(dadosComando.getCliente().getCpfCliente());
			usuarioExistente.getCliente().setPedido(pedido);
			ItemPedidoDTO itemDTo = new ItemPedidoDTO(estiloEscolhido, valor);
			usuarioExistente.getCliente().getPedido().getListaItens().add(itemDTo);
		} else {
			ItemPedidoDTO itemDTo = new ItemPedidoDTO(estiloEscolhido, valor);
			usuarioExistente.getCliente().getPedido().getListaItens().add(itemDTo);

		}
		usuarioExistente.getCliente().getPedido()
				.setValorTotalPedido(usuarioExistente.getCliente().getPedido().getValorTotalPedido() + valor);
		msg.append(CentralMensagensBrewField.ITEM_ADICIONADO_AO_CARRINHO + " ");
		msg.append(dataPadrao.format(new Date()) + " ");
		msg.append(horaPadrao.format(new Date()) + " ");
		msg.append(estiloEscolhido + valor);
		msg.append(CentralMensagensBrewField.PULAR_LINHA);
		msg.append(CentralMensagensBrewField.TOTAL_PARCIAL_DO_CARRINHO
				+ usuarioExistente.getCliente().getPedido().getValorTotalPedido());
		msg.append(CentralMensagensBrewField.PULAR_LINHA);
		msg.append(CentralMensagensBrewField.PULAR_LINHA);
		return msg.toString();
	}

}
