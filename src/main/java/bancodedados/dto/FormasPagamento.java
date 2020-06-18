package bancodedados.dto;

public class FormasPagamento  extends PedidoDTO  {
	private int idPagamento = CentralMensagensBrewField.VALOR_INICIAL_FORMA_PAG;
	private String descPagamento;

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

	public FormasPagamento(String telefone ,int idPagamento, String descPagamento) {
		super(telefone );
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
