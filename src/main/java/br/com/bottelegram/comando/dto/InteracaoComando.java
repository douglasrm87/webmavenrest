package br.com.bottelegram.comando.dto;

import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.PhotoSize;

import bancodedados.dto.ClienteDTO;

public class InteracaoComando {
	private Long idUsuario;
	private String nomeUsuario;
	private String sobreNomeUsuario;
	private int idComando;
	private String complementoComando;
	private ClienteDTO cliente;
	private boolean enviadoFoto = false;
	private String urlRecibo;
	private Contact meuContato;

	public Contact getMeuContato() {
		return this.meuContato;
	}

	public void setMeuContato(Contact meuContato) {
		this.meuContato = meuContato;
	}

	public void reiniciarDados () {
		this.cliente = null;
		this.enviadoFoto = false;
		this.urlRecibo = new String();
	}
	
	public String getUrlRecibo() {
		return this.urlRecibo;
	}

	public void setUrlRecibo(String urlRecibo) {
		this.urlRecibo = urlRecibo;
	}

	public boolean isEnviadoFoto() {
		return this.enviadoFoto;
	}

	public void setEnviadoFoto(boolean enviadoFoto) {
		this.enviadoFoto = enviadoFoto;
	}

	public String getSobreNomeUsuario() {
		return this.sobreNomeUsuario;
	}

	public void setSobreNomeUsuario(String sobreNomeUsuario) {
		this.sobreNomeUsuario = sobreNomeUsuario;
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public int getIdComando() {
		return this.idComando;
	}

	public void setIdComando(int idComando) {
		this.idComando = idComando;
	}

	public String getComplementoComando() {
		return this.complementoComando;
	}

	public void setComplementoComando(String complementoComando) {
		this.complementoComando = complementoComando;
	}

	public InteracaoComando(Long idUsuario, String nomeUsuario, String sobreNomeUsuario, int idComando,
			String complementoComando, ClienteDTO cliente) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.sobreNomeUsuario = sobreNomeUsuario;
		this.idComando = idComando;
		this.complementoComando = complementoComando;
		this.cliente = cliente;// utiliado quando temos cliente cadastrado
	}

	// utiliado quando NÃO temos cliente cadastrado
	public InteracaoComando(Long idUsuario, String nomeUsuario, String sobreNomeUsuario, int idComando,
			String complementoComando) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.sobreNomeUsuario = sobreNomeUsuario;
		this.idComando = idComando;
		this.complementoComando = complementoComando;

	}

	public ClienteDTO getCliente() {
		return this.cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

}
