package br.com.bottelegram.comando.dto;

public class VPNConectadaExcel {
	private String time;
	private String blade;
	private String severity;
	private String action;
	private String origin;
	private String source;
	private String matricula;
	private String user;
	private String destination;
	private String service;
	private String servico;
	private String id;

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBlade() {
		return this.blade;
	}

	public void setBlade(String blade) {
		this.blade = blade;
	}

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getServico() {
		return this.servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	@Override
	public String toString() {
		return "VPNConectadaExcel [time=" + this.time + ", blade=" + this.blade + ", severity=" + this.severity
				+ ", action=" + this.action + ", origin=" + this.origin + ", source=" + this.source + ", user="
				+ this.user + ", destination=" + this.destination + ", service=" + this.service + ", servico="
				+ this.servico + ", id=" + this.id + ", matricula=" + this.matricula + "]";
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
