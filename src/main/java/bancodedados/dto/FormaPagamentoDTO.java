package bancodedados.dto;

import java.util.Date;

public class FormaPagamentoDTO {
	
	
	private int id;
	private int idPedido;
	private int formaPagamento = CentralMensagensBrewField.VALOR_INICIAL_FORMA_PAG;
	private Date dataPagamento = null;
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPedido() {
		return this.idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getFormaPagamento() {
		return this.formaPagamento;
	}
	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
 
	public Date getDataPagamento() {
		return this.dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public FormaPagamentoDTO(int formaPagamento) {
		super();
		this.formaPagamento = formaPagamento;
	}

}
