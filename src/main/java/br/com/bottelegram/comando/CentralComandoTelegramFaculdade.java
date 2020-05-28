package br.com.bottelegram.comando;

import java.util.Date;

import bancodedados.dto.CentralMensagensBrewField;
import br.com.bottelegram.EscopoApplictCSCTimerTelegram;
import br.com.bottelegram.comando.dto.GestaoAtendimento;
import br.com.bottelegram.comando.dto.InteracaoComando;
import br.com.bottelegram.comando.faculdade.ComandoFaculdade;

public class CentralComandoTelegramFaculdade extends CentralComando {

	private static final String CLASSE_COMANDO_REQUERIMENTO_GRADE = "br.com.bottelegram.comando.faculdade.ComandoRequerimentoGrade";
	private static final String CLASSE_COMANDO_CALL_CENTER = "br.com.bottelegram.comando.faculdade.ComandoCallCenter";
	private final static String ACADEMICO = "3 - Acadêmico.\n";
	private final static String FINANCEIRO = "4 - Financeiro.\n";
	private final static String COORDENACAO = "5 - Coordenação.\n";
	private final static String vetOpcoes[] = { CentralMensagensBrewField.FUNCOES, CentralMensagensBrewField.LOGIN, ACADEMICO, FINANCEIRO, COORDENACAO };

	private final static String ACADEMICO_REQUERIMENTO_GRADE = "\nRG - Requerimento Acadêmico - Grade.\n";
	private final static String CALL_CENTER = "CC - Call center.\n";
	private static final String RG = "rg";
	private static final String CC = "cc";
	private final static String vetOpcoesAcademico[] = { ACADEMICO_REQUERIMENTO_GRADE, CALL_CENTER };

	// A partir deste método para cada solicitação feita pelo usuário teremos um
	// retorno especifico.
	@Override
	public String processarComando(InteracaoComando dadosComando) {
		StringBuilder msg = new StringBuilder();

		switch (dadosComando.getIdComando()) {
		case 1:
			msg.append(iniciarTexto(dadosComando.getNomeUsuario(),dadosComando.getSobreNomeUsuario()));
			break;
		case 2:
			msg.append("Informar sua matricula.");
			break;
		case 3:
			String classeJava = null;
			String OpcaoMenu = null;
			if (dadosComando.getComplementoComando()!= null) {
 				if (RG.equalsIgnoreCase(dadosComando.getComplementoComando())) {
					classeJava = CLASSE_COMANDO_REQUERIMENTO_GRADE;
					OpcaoMenu = ACADEMICO_REQUERIMENTO_GRADE;
				}
				if (CC.equalsIgnoreCase(dadosComando.getComplementoComando())) {
					classeJava = CLASSE_COMANDO_CALL_CENTER;
					OpcaoMenu = CALL_CENTER;					
				}
				msg.append(processarAcademico(dadosComando.getComplementoComando(), classeJava));
				EscopoApplictCSCTimerTelegram.listaGestao.add(
						new GestaoAtendimento(dadosComando.getComplementoComando(), null, OpcaoMenu, new Date()));
			} else {
				msg.append(iniciarTextoAcademico(dadosComando.getNomeUsuario(),dadosComando.getSobreNomeUsuario()));
			}

			break;
		case 4:
			msg.append("Contactar Whatsapp Secretária: 41 8530-8359.");
			break;
		case 5:
			msg.append("Coordenador ADS - Douglas Rocha Mendes - 41 992619070.");
			break;

		default:
			msg.append(iniciarTexto(dadosComando.getNomeUsuario(), dadosComando.getSobreNomeUsuario()));
			break;
		}
		return msg.toString();

	}

 

	private String iniciarTextoAcademico(String nomeUsuario,String sobreNomeUsuario) {
		return super.iniciarTexto(nomeUsuario, CentralComandoTelegramFaculdade.vetOpcoesAcademico,false,false);
	}

	private String processarAcademico(String complementoComando, String classeComando) {
		StringBuilder msg = new StringBuilder();
		try {
			Class<?> obj = null;
			obj = Class.forName(classeComando);
			ComandoFaculdade comReqGrade = (ComandoFaculdade) obj.newInstance();
			msg.append(comReqGrade.processarFaculdade(complementoComando));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return msg.toString();
	}
}
