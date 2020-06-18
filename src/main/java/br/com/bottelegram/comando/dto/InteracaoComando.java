package br.com.bottelegram.comando.dto;

import java.util.Arrays;

import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.PhotoSize;

import bancodedados.dto.ClienteDTO;

public class InteracaoComando {
	private int idComando;
	private String complementoComando;
	private String nome;
	private long idUsuarioTelegram;
	private String sobreNome = "";
	private boolean enviadoFoto = false;
	private String urlRecibo;
	private Contact meuContato;
	private PhotoSize[] fotoPagamento;

 	
	
	public InteracaoComando(int idComando, String complementoComando, String nome, long idUsuarioTelegram,
			String sobreNome) {
		super();
		this.idComando = idComando;
		this.complementoComando = complementoComando;
		this.nome = nome;
		this.idUsuarioTelegram = idUsuarioTelegram;
		this.sobreNome = sobreNome;
	}

	public PhotoSize[] getFotoPagamento() {
		return this.fotoPagamento;
	}

	public void setFotoPagamento(PhotoSize[] fotoPagamento) {
		this.fotoPagamento = fotoPagamento;
	}

	public long getIdUsuarioTelegram() {
		return this.idUsuarioTelegram;
	}

	public void setIdUsuarioTelegram(long idUsuarioTelegram) {
		this.idUsuarioTelegram = idUsuarioTelegram;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return this.sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public Contact getMeuContato() {
		return this.meuContato;
	}

	public InteracaoComando( ) {
		super();
	 
	}

	public void setMeuContato(Contact meuContato) {
		this.meuContato = meuContato;
	}

	public void reiniciarDados () {
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

	@Override
	public String toString() {
		return "InteracaoComando [idComando=" + this.idComando + ", complementoComando=" + this.complementoComando + ", nome="
				+ this.nome + ", idUsuarioTelegram=" + this.idUsuarioTelegram + ", sobreNome=" + this.sobreNome + ", enviadoFoto="
				+ this.enviadoFoto + ", urlRecibo=" + this.urlRecibo + ", meuContato=" + this.meuContato + ", fotoPagamento="
				+ Arrays.toString(this.fotoPagamento) + "]";
	}
 

}
