package br.com.bottelegram.comando.dto;

public class Email {
	private String copiaParaDestinatario;
	private String remetente;
	private String assunto;
	private String destinatario;
	private String corpo;

	
	public String getCopiaParaDestinatario() {
		return this.copiaParaDestinatario;
	}

	public void setCopiaParaDestinatario(String copiaParaDestinatario) {
		this.copiaParaDestinatario = copiaParaDestinatario;
	}

	public String getRemetente() {
		return this.remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getAssunto() {
		return this.assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDestinatario() {
		return this.destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getCorpo() {
		return this.corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public Email(String remetente, String assunto, String destinatario, String corpo) {
		super();
		this.remetente = remetente;
		this.assunto = assunto;
		this.destinatario = destinatario;
		this.corpo = corpo;
	}

	public Email(String copiaParaDestinatario, String remetente, String assunto, String destinatario, String corpo) {
		super();
		this.copiaParaDestinatario = copiaParaDestinatario;
		this.remetente = remetente;
		this.assunto = assunto;
		this.destinatario = destinatario;
		this.corpo = corpo;
	}

}
