package br.com.bottelegram;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bottelegram.comando.dto.GestaoAtendimento;

//http://localhost:8080/bottelegram/gestaocsc.jsf
@SessionScoped
@ManagedBean(name = "gestao")
public class GestaoAtendimentoCSC {
//	private static final Logger logger = Logger.getLogger(GestaoAtendimentoCSC.class);
	private List<GestaoAtendimento> listaGestaoJSF = new ArrayList<>();

	public List<GestaoAtendimento> getListaGestaoJSF() {
		return this.listaGestaoJSF;
	}

	public void setListaGestaoJSF(List<GestaoAtendimento> listaGestaoJSF) {
		this.listaGestaoJSF = listaGestaoJSF;
	}

	public void testeGestao() {
		System.out.println("Teste gestao");
	}

	public GestaoAtendimentoCSC() {
		super();
		this.listaGestaoJSF.add(new GestaoAtendimento("123", "456", "teste", new Date()));
		this.listaGestaoJSF.add(new GestaoAtendimento("123", "456", "teste", new Date()));
		this.listaGestaoJSF.add(new GestaoAtendimento("123", "456", "teste", new Date()));
	}

}
