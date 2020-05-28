package br.com.bottelegram.comando.brewfield;

import org.apache.log4j.Logger;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.FormaPagamentoDTO;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoPagamento {

	private static final Logger logger = Logger.getLogger(ComandoPagamento.class);

	public String processarPagamento(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();

		if (CentralMensagensBrewField.AL.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			dadosComando.getCliente().getPedido().setPagamento(null);
			msg.append(CentralMensagensBrewField.FORMA_DE_PAGAMENTO_REMOVIDA);
		}
		if (CentralMensagensBrewField.TB.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			dadosComando.getCliente().getPedido()
					.setPagamento(new FormaPagamentoDTO(CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA));
			msg.append(CentralMensagensBrewField.PAGAMENTO_ESCOLHIDO + CentralMensagensBrewField.TRANSF_BANCARIA);
			msg.append(CentralMensagensBrewField.DADOS_TRANSF_BANCARIA);
		}
		if (CentralMensagensBrewField.PI.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			dadosComando.getCliente().getPedido().setPagamento(new FormaPagamentoDTO(CentralMensagensBrewField.ID_PICPAY));
			msg.append(CentralMensagensBrewField.PAGAMENTO_ESCOLHIDO + CentralMensagensBrewField.PICPAY);
		}
		if (CentralMensagensBrewField.CD.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			dadosComando.getCliente().getPedido().setPagamento(new FormaPagamentoDTO(CentralMensagensBrewField.ID_CARTAO_DEBITO));
			msg.append(CentralMensagensBrewField.PAGAMENTO_ESCOLHIDO + CentralMensagensBrewField.CARTAO_DEBITO);
		}
		if (CentralMensagensBrewField.CC.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			dadosComando.getCliente().getPedido().setPagamento(new FormaPagamentoDTO(CentralMensagensBrewField.ID_CARTAO_CREDITO));
			msg.append(CentralMensagensBrewField.PAGAMENTO_ESCOLHIDO + CentralMensagensBrewField.CARTAO_CREDITO);
		}
		if (CentralMensagensBrewField.AN.equalsIgnoreCase(dadosComando.getComplementoComando())) {
			dadosComando.getCliente().getPedido().setPagamento(new FormaPagamentoDTO(CentralMensagensBrewField.ID_ANDROID_PAY));
			msg.append(CentralMensagensBrewField.PAGAMENTO_ESCOLHIDO + CentralMensagensBrewField.ANDROID_PAY);
		}
		return msg.toString();
	}

}
