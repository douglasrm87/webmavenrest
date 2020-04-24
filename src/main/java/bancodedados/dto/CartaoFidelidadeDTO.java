package bancodedados.dto;

import java.util.Date;

public class CartaoFidelidadeDTO {
	private int idCartao;
	private Date dataInclusao;
	private int cpfCliente;
	 
	public CartaoFidelidadeDTO(Date dataInclusao, int cpfCliente) {
		super();
		this.dataInclusao = dataInclusao;
		this.cpfCliente = cpfCliente;
	}
	public CartaoFidelidadeDTO() {
		super();
	
	}
	public int getCpfCliente() {
		return this.cpfCliente;
	}

	public void setCpfCliente(int cpfCliente) {
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
