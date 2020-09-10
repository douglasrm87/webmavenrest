package bancodedados.faculdade.dto;

import java.util.Date;

public class LOGFaculdade {
	private Long idUsuarioTelegram;
	private String nomeUsuario;
	private Date dataInclusao = new Date();

	private int idPai;
	private int idItem;
	private String descItem;

	@Override
	public String toString() {
		return "LOGFaculdade [idUsuarioTelegram=" + this.idUsuarioTelegram + ", nomeUsuario=" + this.nomeUsuario
				+ ", dataInclusao=" + this.dataInclusao + ", idPai=" + this.idPai + ", idItem=" + this.idItem
				+ ", descItem=" + this.descItem + "]";
	}

	public Long getIdUsuarioTelegram() {
		return this.idUsuarioTelegram;
	}

	public void setIdUsuarioTelegram(Long idUsuarioTelegram) {
		this.idUsuarioTelegram = idUsuarioTelegram;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public int getIdPai() {
		return this.idPai;
	}

	public void setIdPai(int idPai) {
		this.idPai = idPai;
	}

	public int getIdItem() {
		return this.idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getDescItem() {
		return this.descItem;
	}

	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}

	public Date getDataInclusao() {
		return this.dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public LOGFaculdade(Long idUsuarioTelegram, String nomeUsuario, int idPai, int idItem, String descItem,
			Date dataInclusao) {
		super();
		this.idUsuarioTelegram = idUsuarioTelegram;
		this.nomeUsuario = nomeUsuario;
		this.idPai = idPai;
		this.idItem = idItem;
		this.descItem = descItem;
		this.dataInclusao = dataInclusao;
	}

	public LOGFaculdade() {
		super();
	}

}
