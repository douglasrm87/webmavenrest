package br.com.bottelegram.comando;

import java.util.Date;

import bancodedados.dto.CentralMensagensBrewField;
import br.com.bottelegram.EscopoApplictCSCTimerTelegram;
import br.com.bottelegram.comando.dto.GestaoAtendimento;
import br.com.bottelegram.comando.dto.InteracaoComando;
import br.com.bottelegram.comando.telecom.ComandoPing;
import br.com.bottelegram.comando.telecom.ComandoVPNConectada;
import br.com.bottelegram.comando.telecom.ComandoWakeOnLAN;

public class CentralComandoTelegramRede extends CentralComando {
	private final static String FORCE_POINT = "\nConectado na VPN ForcePoint.\n";
	private static final String INFORMAR_O_IP_OU_PAT = "Informar o IP ou PAT";
	private final static String VPN_CONECTADA = "3 - VPN de Home Office.\n";
	private final static String PING_V4 = "4 - ping IP/PAT.\n";
	private final static String LIGAR_PC = "5 - Ligar PC.\n";
	private final static String INSTALAR_VPN = "6 - Instalar VPN.\n";
	private final static String SENHA_ADM = "7 - Senha Administrador.\n";
	private final static String ANTI_VIRUS = "8 - Instalar Anti Virus.\n";
	private final static String vetOpcoes[] = { CentralMensagensBrewField.FUNCOES,   CentralMensagensBrewField.LOGIN, VPN_CONECTADA, PING_V4, LIGAR_PC,
			INSTALAR_VPN, SENHA_ADM, ANTI_VIRUS };

	// A partir deste método para cada solicitação feita pelo usuário teremos um
	// retorno especifico.
	@Override
	protected String processarComando(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();

		switch (dadosComando.getIdComando()) {
		case 1:
			msg.append(iniciarTexto(dadosComando.getNomeUsuario(),dadosComando.getSobreNomeUsuario()));
			break;
		case 2:
			msg.append(CentralMensagensBrewField.LOGIN_RESULT);
			break;

		case 3:
			if (dadosComando.getComplementoComando() != null) {
				ComandoVPNConectada comVPN = new ComandoVPNConectada();
				msg.append(comVPN.localizarVPN(dadosComando.getComplementoComando()));
				EscopoApplictCSCTimerTelegram.listaGestao.add(
						new GestaoAtendimento(dadosComando.getComplementoComando(), null, VPN_CONECTADA, new Date()));
			} else {
				msg.append(CentralMensagensBrewField.INFORMAR_MATRICULA);
			}
			break;
		case 4:
			if (dadosComando.getComplementoComando() != null) {
				ComandoPing ping = new ComandoPing();
				msg.append(ping.processar(dadosComando.getComplementoComando()));
				EscopoApplictCSCTimerTelegram.listaGestao
						.add(new GestaoAtendimento(dadosComando.getComplementoComando(), null, PING_V4, new Date()));
			} else {
				msg.append(INFORMAR_O_IP_OU_PAT);
			}
			break;
		case 5:
			if (dadosComando.getComplementoComando() != null) {
				ComandoWakeOnLAN ligar = new ComandoWakeOnLAN();
				msg.append(ligar.ligarDesktop(dadosComando.getComplementoComando()));
				EscopoApplictCSCTimerTelegram.listaGestao
						.add(new GestaoAtendimento(null, dadosComando.getComplementoComando(), PING_V4, new Date()));
			} else {
				msg.append(INFORMAR_O_IP_OU_PAT);
			}
			break;
		default:
			msg.append(iniciarTexto(dadosComando.getNomeUsuario(),dadosComando.getSobreNomeUsuario()));
			break;
		}
		return msg.toString();
	}
 
}
