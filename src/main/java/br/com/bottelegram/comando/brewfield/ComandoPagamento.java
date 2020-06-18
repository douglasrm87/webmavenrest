package br.com.bottelegram.comando.brewfield;

import org.apache.log4j.Logger;

import bancodedados.PostgreSQLJDBCPagamentoDML;
import bancodedados.PostgreSQLJDBCPedidoDML;
import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.FormasPagamento;
import bancodedados.dto.MenuGrafico;
import bancodedados.dto.PedidoDTO;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoPagamento {

	private static final Logger logger = Logger.getLogger(ComandoPagamento.class);

	public String processarPagamento(InteracaoComando dadosComando, ClienteDTO clienteTelegram) {
		StringBuilder msg = new StringBuilder();
		FormasPagamento pagamento = null;

		if (CentralMensagensBrewField.TRANSF_BANCARIA.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			pagamento = new FormasPagamento(clienteTelegram.getTelefone(),
					CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA, CentralMensagensBrewField.TRANSF_BANCARIA);
			msg.append(CentralMensagensBrewField.PAGAMENTO_ESCOLHIDO + CentralMensagensBrewField.TRANSF_BANCARIA);
			msg.append(CentralMensagensBrewField.DADOS_TRANSF_BANCARIA);
		}
		if (CentralMensagensBrewField.PICPAY.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			pagamento = new FormasPagamento(clienteTelegram.getTelefone(), CentralMensagensBrewField.ID_PICPAY,
					CentralMensagensBrewField.PICPAY);
			msg.append(CentralMensagensBrewField.PAGAMENTO_ESCOLHIDO + CentralMensagensBrewField.PICPAY);
		}
		if (CentralMensagensBrewField.CARTAO_DEBITO.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			pagamento = new FormasPagamento(clienteTelegram.getTelefone(), CentralMensagensBrewField.ID_CARTAO_DEBITO,
					CentralMensagensBrewField.CARTAO_DEBITO);
			msg.append(CentralMensagensBrewField.PAGAMENTO_ESCOLHIDO + CentralMensagensBrewField.CARTAO_DEBITO);
		}
		if (CentralMensagensBrewField.CARTAO_CREDITO.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			pagamento = new FormasPagamento(clienteTelegram.getTelefone(), CentralMensagensBrewField.ID_CARTAO_CREDITO,
					CentralMensagensBrewField.CARTAO_CREDITO);
			msg.append(CentralMensagensBrewField.PAGAMENTO_ESCOLHIDO + CentralMensagensBrewField.CARTAO_CREDITO);
		}
		if (pagamento != null) {
			PostgreSQLJDBCPedidoDML ped = new PostgreSQLJDBCPedidoDML();
			PedidoDTO pedido = ped.selecionarPedidoAbertoByTelefone(clienteTelegram.getTelefone());
			PostgreSQLJDBCPagamentoDML pag = new PostgreSQLJDBCPagamentoDML();
			pag.cadastrarPagamentoPedidoDTO(pagamento, pedido);
			MenuGrafico menu = new MenuGrafico();
			menu.zerarBotoesBaixo(dadosComando.getIdUsuarioTelegram());
			msg.append(CentralMensagensBrewField.AGORA_SO_FALTA_CONFIRMAR_SEU_PEDIDO);
		}

		return msg.toString();
	}

}
