package bancodedados.dto;
//https://core.telegram.org/bots/api
//https://core.telegram.org/bots/payments#supported-currencies
public class FormasPagamento extends PedidoDTO {
	private int idPagamento = CentralMensagensBrewField.VALOR_INICIAL_FORMA_PAG;
	private String descPagamento;
	public final static String API_KEY_ASAAS = "0ed121e4e6050e17df5019ab8173df986b25025330c31ddcd9dc3667ef973a13";
	
	public int getIdPagamento() {
		return this.idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getDescPagamento() {
		return this.descPagamento;
	}

	public void setDescPagamento(String descPagamento) {
		this.descPagamento = descPagamento;
	}

	public FormasPagamento(String telefone, int idPagamento, String descPagamento) {
		super(telefone);
		this.idPagamento = idPagamento;
		this.descPagamento = descPagamento;
	}

	public FormasPagamento(int idPagamento, String descPagamento) {
		this.idPagamento = idPagamento;
		this.descPagamento = descPagamento;
	}

	public FormasPagamento() {
	}

}
