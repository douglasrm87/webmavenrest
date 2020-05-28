package bancodedados.dto;

import java.util.Date;

public class CartaoFidelidadeDTO {
	private int idCartao;
	private Date dataInclusao;
	private long cpfCliente;
	 
	public CartaoFidelidadeDTO() { }
	public CartaoFidelidadeDTO(Date dataInclusao, long cpfCliente) {
		super();
		this.dataInclusao = dataInclusao;
		this.cpfCliente = cpfCliente;
	}
 
	public long getCpfCliente() {
		return this.cpfCliente;
	}

	public void setCpfCliente(long cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
 
	public Date getDataInclusao() {
		return this.dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public int getIdCartao() {
		return this.idCartao;
	}

	// Por se auto increment não faz sentido ter o metodo set...
//	public void setIdCartao(int idCartao) {
//		this.idCartao = idCartao;
//	}
}
