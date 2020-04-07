package postgree.dto;

import java.util.Date;

public class CartaoFidelidadeDTO {

	private Date dataInclusao;
	
	public Date getDataInclusao() {
		return this.dataInclusao;
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public CartaoFidelidadeDTO(Date dataInclusao) {
		super();
		this.dataInclusao = dataInclusao;
	}
	
	

}
