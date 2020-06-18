package bancodedados.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BonusCartaoFidelidadeDTO { 
	private int idBonus;
	private String telefone;

	private int bonusConsumido = 0; // 0 não consumido, 1 consumido
	private int bonusAtivado = 0; // 0 desativado , 1 ativado 
	private Date dataInclusao = new Date();
	private Date dataVencimento = new Date();
	

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getBonusConsumido() {
		return this.bonusConsumido;
	}

	public void setBonusConsumido(int bonusConsumido) {
		this.bonusConsumido = bonusConsumido;
	}

	public int getBonusAtivado() {
		return this.bonusAtivado;
	}

	public void setBonusAtivado(int bonusAtivado) {
		this.bonusAtivado = bonusAtivado;
	}

	public int getIdBonus() {
		return this.idBonus;
	}

	public void setIdBonus(int idBonus) {
		this.idBonus = idBonus;
	}
 
	public Date getDataInclusao() {
		return this.dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

}
