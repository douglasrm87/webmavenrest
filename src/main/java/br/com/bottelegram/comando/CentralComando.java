package br.com.bottelegram.comando;

import java.util.Calendar;

import org.apache.log4j.Logger;

import bancodedados.dto.CentralMensagensBrewField;
import br.com.bottelegram.comando.brewfield.ComandoCarrinho;
import br.com.bottelegram.comando.dto.InteracaoComando;

public abstract class CentralComando {

	// A partir deste método para cada solicitação feita pelo usuário teremos um
	// retorno especifico.
	protected abstract String processarComando(InteracaoComando dadosComando);

	protected String iniciarTexto(String nomeUsuario, String vetOpcoes[], boolean msgBemVindo, boolean saudacaoFecha) {
		StringBuilder msg = new StringBuilder();
		if (msgBemVindo) {
			msg.append(saudacaoTempo() + nomeUsuario + CentralMensagensBrewField.SAUDACAO);
		} else {
			if (saudacaoFecha) {
				msg.append(CentralMensagensBrewField.PULAR_LINHA + CentralMensagensBrewField.PULAR_LINHA + nomeUsuario
						+ CentralMensagensBrewField.SAUDACAO_FECHAMENTO);
			}
		}
		for (int i = 0; i < vetOpcoes.length; i++) {
			msg.append(vetOpcoes[i]);
		}
		return msg.toString();
	}

	protected String saudacaoTempo() {
		Calendar cal = Calendar.getInstance();
		int hora = cal.get(Calendar.HOUR_OF_DAY);
		if (hora >= 6 && hora <= 12) {
			return CentralMensagensBrewField.BOM_DIA;
		} else {
			if (hora > 12 && hora <= 18) {
				return CentralMensagensBrewField.BOA_TARDE;
			}
			return CentralMensagensBrewField.BOA_NOITE;
		}
	}

	protected String menuAposEscolhaPagamento(String nomeUsuario, String sobreNomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesAposPagamento, false, false);
	}

	protected String menuFechamento(String nomeUsuario, String sobreNomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesFechamento, false, false);
	}

	protected String refazerlogin(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();
		msg.append(CentralMensagensBrewField.REALIZAR_LOGIN_E_ESCOLHER_SEU_GROWLER);
		dadosComando.setComplementoComando(null);
		msg.append(menuSomenteLogin(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
		return msg.toString();
	}

	protected String jaLogadoContinuar(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();
		dadosComando.setComplementoComando(null);
		msg.append(iniciarTexto(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
		return msg.toString();
	}

	protected String iniciarTextoEstilos(String nomeUsuario, String sobreNomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesBrewField, false, false);
	}

	protected String iniciarTexto(String nomeUsuario, String sobreNomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesParcial, true, false);
	}
	protected String menuSomenteLogin(String nomeUsuario, String sobreNomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetSomenteLogin, true, false);
	}

	protected String menuJaLogadoContinuar(String nomeUsuario, String sobreNomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesJaLogadoContinuar, true, false);
	}

	protected String menuUsuarioCadastrado(String nomeUsuario, String sobreNomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesUsuarioJaCadastrado, false, false);
	}

	protected String apresentarMenuFecharPedido(String nomeUsuario, String sobreNomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesCompleto, false, true);
	}

	protected String iniciarTextoNoLogin(String nomeUsuario, String sobreNomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesNoLogin, false, false);
	}

	protected String menuPagamento(String nomeUsuario, String sobreNomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesPagamento, false, false);
	}

}
