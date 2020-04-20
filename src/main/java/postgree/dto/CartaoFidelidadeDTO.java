package postgree.dto;

import java.util.Date;

public class CartaoFidelidadeDTO {
	private int idCartao;
	private Date dataInclusao;

	public CartaoFidelidadeDTO(Date dataInclusao) {
		super();
		this.dataInclusao = dataInclusao;
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

//	public void setIdCartao(int idCartao) {
//		this.idCartao = idCartao;
//	}
}
