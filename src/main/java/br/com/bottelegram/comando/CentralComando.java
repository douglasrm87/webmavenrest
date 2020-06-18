package br.com.bottelegram.comando;

import java.util.Calendar;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.MenuGrafico;
import br.com.bottelegram.comando.dto.InteracaoComando;

public abstract class CentralComando {

	protected String iniciarTexto(String nomeUsuario, String vetOpcoes[], boolean msgBemVindo, boolean saudacaoFecha) {
		StringBuilder msg = new StringBuilder();
		if (msgBemVindo) {
			msg.append(saudacaoTempo() + nomeUsuario + CentralMensagensBrewField.SAUDACAO);
		} else {
			if (saudacaoFecha) {
				msg.append(CentralMensagensBrewField.PULAR_LINHA + "<b>" + nomeUsuario + "</b>"
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

//	protected String menuAposEscolhaPagamento(String nomeUsuario, String sobreNomeUsuario) {
//		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesAposPagamento, false, false);
//	}

//	protected String menuFechamento(String nomeUsuario) {
//		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesFechamento, false, false);
//	}

	protected String refazerlogin(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();
		msg.append(CentralMensagensBrewField.REALIZAR_LOGIN_E_ESCOLHER_SEU_GROWLER);
		dadosComando.setComplementoComando(null);
		msg.append(menuSomenteLogin(dadosComando.getNome()));
		return msg.toString();
	}

	protected String jaLogadoContinuar(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();
		dadosComando.setComplementoComando(null);
		msg.append(iniciarTexto(dadosComando.getNome()));
		return msg.toString();
	}

	protected void iniciarTextoEstilos(ClienteDTO clienteTelegram) {
		MenuGrafico menu = new MenuGrafico();
		menu.botoesEstilosCervejasLinhasDin(clienteTelegram.getIdUsuarioTelegram());
	}

	protected String iniciarTexto(String nomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesParcial, true, false);
	}

	protected String menuSomenteLogin(String nomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetSomenteLogin, true, false);
	}

	protected void menuJaLogadoContinuar(ClienteDTO clienteTelegram) {
		MenuGrafico menu = new MenuGrafico();
		menu.zerarBotoesBaixo(clienteTelegram.getIdUsuarioTelegram());
		menu.menuOpcoesGrafico(clienteTelegram.getIdUsuarioTelegram());
	}
 

	protected String apresentarMenuFecharPedido(String nomeUsuario) {
		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesCompleto, false, true);
	}

//	protected String iniciarTextoNoLogin(String nomeUsuario) {
//		return iniciarTexto(nomeUsuario, CentralMensagensBrewField.vetOpcoesNoLogin, false, false);
//	}

	protected void menuPagamento(ClienteDTO clienteTelegram) {
		MenuGrafico menu = new MenuGrafico();
		menu.zerarBotoesBaixo(clienteTelegram.getIdUsuarioTelegram());
		menu.botoesFormasPAgamento(clienteTelegram.getIdUsuarioTelegram());

	}

}
