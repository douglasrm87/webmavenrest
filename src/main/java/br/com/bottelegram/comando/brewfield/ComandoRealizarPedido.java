package br.com.bottelegram.comando.brewfield;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import bancodedados.PostgreSQLJDBCPedidoDML;
import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.ItemPedidoDTO;
import bancodedados.dto.MenuGrafico;
import bancodedados.dto.PedidoDTO;
import bancodedados.dto.ProdutoBebida;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoRealizarPedido {

//	private static final Logger logger = Logger.getLogger(ComandoRealizarPedido.class);

	public String processarPedido() {
		StringBuilder msg = new StringBuilder();
		msg.append(CentralMensagensBrewField.ESCOLHA_UM_DOS_ESTILOS_DISPONIVEIS);
		return msg.toString();
	}

	public String processarPedido(InteracaoComando dadosComando, ClienteDTO clienteTelegram) {
		StringBuilder msg = new StringBuilder();
		MenuGrafico menu = new MenuGrafico();
		List<ProdutoBebida> listaCervejas = menu.carregarProdutoCerveja();
		double valor = 0.0;
		String estiloEscolhido = new String();
		boolean estiloValido = false;
		String meuPedido[] = dadosComando.getComplementoComando().split("-");
		if (meuPedido != null && meuPedido.length == 3 && CentralMensagensBrewField.ADD.equals(meuPedido[0])) {
			for (ProdutoBebida produto : listaCervejas) {
				if (meuPedido[1].equalsIgnoreCase(produto.getDescricaoCurta())) {
					valor = produto.getValorLitro();
					estiloEscolhido = produto.getDescricaoCurta();
					estiloValido = true;
					break;
				}
			}
		}

		if (estiloValido) {
			msg.append(processarPedido(valor, estiloEscolhido, clienteTelegram));
		} else {
			return null;
		}

		return msg.toString();
	}

	private String processarPedido(double valor, String estiloEscolhido, ClienteDTO cliente) {
		StringBuilder msg = new StringBuilder();
		DateFormat dataPadrao = DateFormat.getDateInstance(DateFormat.DEFAULT);
		DateFormat horaPadrao = DateFormat.getTimeInstance(DateFormat.DEFAULT);
		PostgreSQLJDBCPedidoDML ped = new PostgreSQLJDBCPedidoDML();
		ItemPedidoDTO item = new ItemPedidoDTO(estiloEscolhido, valor, cliente.getTelefone());
		double totalParcial = 0.0;
		msg.append(dataPadrao.format(new Date()) + " ");
		msg.append(horaPadrao.format(new Date()) + " ");
		msg.append(CentralMensagensBrewField.PULAR_LINHA);
		if (cliente.getPedido() == null && valor > 0) {
			totalParcial = valor;
			PedidoDTO pedido = new PedidoDTO(cliente.getTelefone(), 0.0, CentralMensagensBrewField.ABERTO);
			ped.cadastrarPedidoDTO(pedido);

			pedido = ped.selecionarPedidoAbertoByTelefone(cliente.getTelefone());
			ped.cadastrarItemPedidoDTO(item, pedido);
			pedido.setListaItens(ped.selecionarItemPedidoAbertoByTelefone(pedido));

			cliente.setPedido(pedido);
			msg.append(CentralMensagensBrewField.ITEM_ADICIONADO_AO_CARRINHO + " ");
		} else {
			ped.cadastrarItemPedidoDTO(item, cliente.getPedido());
			cliente.getPedido().setListaItens(ped.selecionarItemPedidoAbertoByTelefone(cliente.getPedido()));
			for (ItemPedidoDTO itemPedidoDTO : cliente.getPedido().getListaItens()) {
				if (!CentralMensagensBrewField.MSG_TAXA.equalsIgnoreCase(itemPedidoDTO.getEstiloCerveja()))
					totalParcial += itemPedidoDTO.getValorCerveja();
			}
			msg.append(CentralMensagensBrewField.ITEM_ADICIONADO_AO_CARRINHO + " ");
		}
		msg.append(estiloEscolhido + CentralMensagensBrewField.REAL + valor);
		msg.append(CentralMensagensBrewField.PULAR_LINHA);
		msg.append(CentralMensagensBrewField.TOTAL_PARCIAL_DO_CARRINHO + totalParcial);
		msg.append(CentralMensagensBrewField.PULAR_LINHA);
		msg.append(CentralMensagensBrewField.PULAR_LINHA);
		return msg.toString();
	}

}
