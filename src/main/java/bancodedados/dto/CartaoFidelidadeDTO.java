package bancodedados.dto;

import java.util.Date;

public class CartaoFidelidadeDTO {
	private int idPedido;
	private int idBonus;
	private String telefone;
	
	private int estadoSeloCartao = 0; // 0 nao consumido/aberto, 1 consumido/fechado.
	private Date dataInclusao = new Date();
	private Date dataVencimento = new Date();
 
	public int getIdPedido() {
		return this.idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdBonus() {
		return this.idBonus;
	}
	public void setIdBonus(int idBonus) {
		this.idBonus = idBonus;
	}
	public String getTelefone() {
		return this.telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getEstadoSeloCartao() {
		return this.estadoSeloCartao;
	}
	public void setEstadoSeloCartao(int estadoSeloCartao) {
		this.estadoSeloCartao = estadoSeloCartao;
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
