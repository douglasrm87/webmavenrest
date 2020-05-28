package br.com.bottelegram.comando.dto;

import java.util.Date;

public class GestaoAtendimento {
	private String matricula;
	private String pat;
	private String comando;
	private Date dtHoraSolicitacao;

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPat() {
		return this.pat;
	}

	public void setPat(String pat) {
		this.pat = pat;
	}

	public String getComando() {
		return this.comando;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}

	public Date getDtHoraSolicitacao() {
		return this.dtHoraSolicitacao;
	}

	public void setDtHoraSolicitacao(Date dtHoraSolicitacao) {
		this.dtHoraSolicitacao = dtHoraSolicitacao;
	}

	public GestaoAtendimento(String matricula, String pat, String comando, Date dtHoraSolicitacao) {
		super();
		this.matricula = matricula;
		this.pat = pat;
		this.comando = comando;
		this.dtHoraSolicitacao = dtHoraSolicitacao;
	}

}
