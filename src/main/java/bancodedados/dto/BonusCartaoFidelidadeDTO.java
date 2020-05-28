package bancodedados.dto;

import java.util.Date;

public class BonusCartaoFidelidadeDTO {
	private int idBonus;
	private Date dataInclusao;
	private long cpfCliente;
	 
	public BonusCartaoFidelidadeDTO(Date dataInclusao, long cpfCliente) {
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
 
}
