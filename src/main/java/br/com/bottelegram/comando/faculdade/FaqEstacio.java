package br.com.bottelegram.comando.faculdade;

import java.util.ArrayList;
import java.util.List;

public class FaqEstacio {
	private static final String DOUGLAS_MENDES_ESTACIO_BR = "\nEnviar email para: douglas.mendes@estacio.br";
	private static final String SSA_CTBA_ESTACIO_BR2 = "\nADS. Enviar email para: \nmariana.veloso@estacio.br\nssa.ctba@estacio.br";
	private static final String FIES_CURITIBA_ESTACIO_BR = "\nEnviar email para: fies.curitiba@estacio.br";
	private static final String PROUNI_CURITIBA_ESTACIO_BR = "\nEnviar email para: prouni.curitiba@estacio.br";
	private static final String COLACAO_CTBA_ESTACIO_BR = "\nEnviar email para: colacao.ctba@estacio.br";

	private static final String HORAS_COMPLEMENTARES = "AACs: ";
	private static final String ACESSAR_MINHAS_AULAS_TEAMS = "Acessar aulas Teams.";
	private static final String ASSITIR_MINHAS_AULAS_TEAMS = "Assistir aulas Teams.";
	private static final String EMAIL_INSTITUCIONAL = "Email Institucional.";
	private static final String ACESSAR_AULAS_GRAVADAS_TEAMS = "Aulas gravadas.";

	private static final String AJUSTAR_GRADE = "Ajustar grade: ";
	private static final String VISUALIZAR_GRADE_2020_2 = "\nVisualizar grade 2020.2: ";
	private static final String DOC_FORMATURA = "\nDoc. formatura. ";
	private static final String SEGUNDA_A_SEXTA_FEIRA_DAS_10H30_AS_20H = "\nsegunda a sexta feira das 10h30 as 20h.";
	private static final String SEGUNDA_A_SEXTA_FEIRA_DAS_9H30_AS_17H30 = "\nsegunda a sexta feira das 9h30 as 17h30.";
	private static final String EDLAINE_TAMANINI_ESTACIO_BR = "\nedlaine.tamanini@estacio.br";

	private static final String DOUGLAS_MENDES_CEL = "\nWhatsapp: 41 99261-9070";
	private static final String _41_98530_8359 = "\nWhatsapp: 41 98530-8359";
	private static final String _41_98879_8791 = "\nWhatsapp: 41 98879-8791";

	private static final String COLACAO_DE_GRAU = "\nCola��o de Grau: ";
	private static final String GRADE_VAZIA = "\nGrade vazia: ";
	private static final String ENTENDA_SEUS_CREDITOS = "Entenda seus cr�ditos: ";
	private static final String DISCIPLINAS_ELETIVAS = "\nDisciplinas eletivas: ";
	private static final String DUVIDA_PRINCIPAL = "\nD�vida Principal: ";
	private static final String REQUERIMENTO = "Requerimento: ";
	private static final String FIES = "FIES: ";
	private static final String RENOVACAO_DO_PAR = "Renova��o do PAR: ";
	private static final String VDI_TI_NA_NUVEM = "VDI TI na Nuvem: ";
	private static final String PROUNI = "PROUNI: ";
	private static final String ESTAGIO_PROFISSIONAL = "Est�gio Profissional: ";
	private static final String BIBLIOTECA2 = "Biblioteca";
	private static final String GRADE2 = "Grade";
	private static final String BOLETO2 = "Boleto";
	private static final String FINANCEIRO2 = "Financeiro";
	private static final String SECRETARIA2 = "Matricula/Secret";
	private static final String ACADEMICO2 = "Academico";
	private static final String COORDENADOR_ADS = "Coordenador ADS";
	private static final String DATAS_IMPORTANTES = "\nDatas importantes: ";
	private static final String AVALIACOES = "\nAvalia��es";
	private static final String CONTATO = "\nContato: ";
	public static final int PAI = 0;
	public static final int ACADEMICO_ID = 1;
	public static final int SECRETARIA_ID = 2;
	public static final int FINANCEIRO_ID = 3;
	public static final int BOLETO_ID = 4;
	public static final int GRADE_ID = 5;
	public static final int BIBLIOTECA_ID = 6;
	public static final int COORDENACAO_ADS_ID = 7;
	public static final int DATAS_IMPORTANTES_ID = 8;

	public List<FAQ> carregarNivel01() {
		List<FAQ> nivel01 = new ArrayList<>();
		FAQ duvida;
		duvida = new FAQ(PAI, ACADEMICO_ID, ACADEMICO2);
		nivel01.add(duvida);
		duvida = new FAQ(PAI, SECRETARIA_ID, SECRETARIA2);
		nivel01.add(duvida);
		duvida = new FAQ(PAI, FINANCEIRO_ID, FINANCEIRO2);
		nivel01.add(duvida);
		duvida = new FAQ(PAI, BOLETO_ID, BOLETO2);
		nivel01.add(duvida);
		duvida = new FAQ(PAI, GRADE_ID, GRADE2);
		nivel01.add(duvida);
		duvida = new FAQ(PAI, BIBLIOTECA_ID, BIBLIOTECA2);
		nivel01.add(duvida);
		duvida = new FAQ(PAI, COORDENACAO_ADS_ID, COORDENADOR_ADS);
		nivel01.add(duvida);

		duvida = new FAQ(PAI, DATAS_IMPORTANTES_ID, AVALIACOES);
		nivel01.add(duvida);
		return nivel01;
	}

	public List<FAQ> carregarNivelAcademico() {
		List<FAQ> nivel01 = new ArrayList<>();
		FAQ duvida;
		String telefone = _41_98879_8791;
		String email = EDLAINE_TAMANINI_ESTACIO_BR;
		String horarios = SEGUNDA_A_SEXTA_FEIRA_DAS_10H30_AS_20H;
		String requerimento = "\nAbrir Requerimento no SIA: Atendimento > requerimento > novo > Estagio";
		String urlVDI = "\nTI na Nuvem. http://estaciobr.cloud.com ";

		duvida = new FAQ(ACADEMICO_ID, 1, CONTATO, telefone + email + horarios);
		nivel01.add(duvida);
		duvida = new FAQ(ACADEMICO_ID, 2, ESTAGIO_PROFISSIONAL, requerimento);
		nivel01.add(duvida);
		duvida = new FAQ(ACADEMICO_ID, 3, VDI_TI_NA_NUVEM, urlVDI);
		nivel01.add(duvida);
		String requerimentoAAC = "\nPara incluir horas complementares abrir requerimento:\n"
				+ "SIA - Atendimento > novo > nota/presen�a/atividades complementares > lan�amento de atividade externas";
		duvida = new FAQ(ACADEMICO_ID, 4, HORAS_COMPLEMENTARES, requerimentoAAC);
		nivel01.add(duvida);

		duvida = new FAQ(ACADEMICO_ID, 5, ACESSAR_MINHAS_AULAS_TEAMS, "\nComo <b>acessar</b> minhas aulas no Teams.",
				true, "Comoacessarminhasaulas.pdf");
		nivel01.add(duvida);

		duvida = new FAQ(ACADEMICO_ID, 6, ASSITIR_MINHAS_AULAS_TEAMS, "\nComo <b>assistir</b> aulas online no Teams.",
				true, "Comoassistiraulasonline.pdf");
		nivel01.add(duvida);

		duvida = new FAQ(ACADEMICO_ID, 7, EMAIL_INSTITUCIONAL, "\nComo <b>criar o email institucional</b>.", true,
				"Comocriaroemailinstitucional.pdf");
		nivel01.add(duvida);

		duvida = new FAQ(ACADEMICO_ID, 8, ACESSAR_AULAS_GRAVADAS_TEAMS,
				"\\nComo <b>assistir</b> aulas gravadas no Teams.", true, "Comoassistiraulasgravadas.pdf");
		nivel01.add(duvida);

		return nivel01;
	}

	public List<FAQ> carregarNivelSecretaria() {
		List<FAQ> nivel01 = new ArrayList<>();
		FAQ duvida;
		String telefone = _41_98530_8359;
		String email = SSA_CTBA_ESTACIO_BR2;
		String horarios = SEGUNDA_A_SEXTA_FEIRA_DAS_9H30_AS_17H30;
		duvida = new FAQ(SECRETARIA_ID, 1, CONTATO, telefone + email + horarios);
		nivel01.add(duvida);
		return nivel01;
	}

	public List<FAQ> carregarNivelFinanceiro() {
		List<FAQ> nivel01 = new ArrayList<>();
		FAQ duvida;
		String emailPROUNI = PROUNI_CURITIBA_ESTACIO_BR;
		String emailFIES = FIES_CURITIBA_ESTACIO_BR;
		String horarios = SEGUNDA_A_SEXTA_FEIRA_DAS_10H30_AS_20H;
		String requerimentoPAR = "\nAbrir Requerimento no SIA -> Bolsas -> Financiamento -> PAR";

		duvida = new FAQ(FINANCEIRO_ID, 1, RENOVACAO_DO_PAR, requerimentoPAR);
		nivel01.add(duvida);
		duvida = new FAQ(FINANCEIRO_ID, 2, PROUNI, emailPROUNI);
		nivel01.add(duvida);
		duvida = new FAQ(FINANCEIRO_ID, 3, FIES, emailFIES);
		nivel01.add(duvida);

		return nivel01;
	}

	public List<FAQ> carregarNivelBoleto() {
		List<FAQ> nivel01 = new ArrayList<>();
		FAQ duvida;
		String duvidaPrincipal = "\nValor da mensalidade aumentou mesmo possuindo n�mero similar de disciplinas. Porque? \r\n"
				+ "O boleto do m�s subsequente (Ex.: Agosto/2020) vem com os valores retroativos a julho/2020, pois as disciplinas s�o semestrais, ou seja,	voc� paga ela por 6 meses.";
		String requerimentoBoleto = "\nAbrir Requerimento no SIA: Atendimento > Requerimentos > Novo > Financeiro/Financiamento/Bolsa/Analise Boleto";
		String discCreditos = "\nCada disciplina possui um n�mero fixo de cr�ditos. A modalidade de cobran�a esta relacionada aos cr�ditos de cada "
				+ "disciplina, Desse modo, o valor de sua mensalidade ser� em acordo com a quantidade de cr�ditos inclu�dos."
				+ "\nPor padr�o em cada semestre o aluno dever� matricular-se em disciplinas que somem de 8 a 40 cr�ditos. Caso o aluna"
				+ "deseja reduzir ou aumentar os cr�dito dever� contactar a coordena��o.";

		duvida = new FAQ(BOLETO_ID, 1, DUVIDA_PRINCIPAL, duvidaPrincipal);
		nivel01.add(duvida);
		duvida = new FAQ(BOLETO_ID, 2, REQUERIMENTO, requerimentoBoleto);
		nivel01.add(duvida);
		duvida = new FAQ(BOLETO_ID, 3, ENTENDA_SEUS_CREDITOS, discCreditos);
		nivel01.add(duvida);

		return nivel01;
	}

	public List<FAQ> carregarNivelGrade() {
		List<FAQ> nivel01 = new ArrayList<>();
		FAQ duvida;
		String eletivas = "\nAluno dever� cursar uma disciplina do grupo G1 e uma do grupo G2. (Modelo do curso de ADS - Para conhecer as disciplinas dos grupos G1 e G2 analise seu hist�rco escolar. "
				+ "	Aten��o: A depender do curr�culo, verifique com seu coordenador se a regra permanece. ";
		String gradeVazia = "\nSe Mensagem do SIA for: Turmas Indispon�veis. Para mais informa��es, entre em contato com a sua Coordena��o ... \n"
				+ "Verificar quais disciplinas faltam em seu Hist�rico Escolar para fechar o curso. "
				+ "Realizaremos estudo de viabilidade para abertura de turmas EDO (Estudo Dirigido Online) ou EDP (Estudo Dirigido Presencial) ap�s abertura do requerimento. "
				+ "Abrir seu requerimento no SIA: Atendimento > Requerimentos > novo > monografia/TCC/cola��o/diploma > cursar mat�ria indispon�vel para formando"
				+ "\n\n<b>Aten��o 01:</b> No requerimento informar o C�digo (ID) da Disciplina e seu NOME. Sem estes dados poder� ter seu requerimento Indeferido pelo CSC. "
				+ "\n<b>Aten��o 02:</b> A disciplina ser� presencial e digital no modelo EDP - Ensino Dirigido Presencial. Aten��o: Da carga hor�ria semanal Ser� 50% em sala de aula/Teams e 50% somente digital. Os valores das disciplinas seguem os mesmos modelos das disciplinas que cursaram nos �ltimos semestres. Programada para iniciar no pr�ximo m�s (Ex.: em 2020.2 ser� setembro)."
				+ "\n<b>Aten��o 03:</b> Para realizar sua rematricula exige estar tudo quitado.";

		duvida = new FAQ(GRADE_ID, 1, DISCIPLINAS_ELETIVAS, eletivas);
		nivel01.add(duvida);
		duvida = new FAQ(GRADE_ID, 2, GRADE_VAZIA, gradeVazia);
		nivel01.add(duvida);
		duvida = new FAQ(GRADE_ID, 3, COLACAO_DE_GRAU, COLACAO_CTBA_ESTACIO_BR);
		nivel01.add(duvida);
		duvida = new FAQ(GRADE_ID, 4, DOC_FORMATURA, " Abrir Requerimento no SIA: \r\n"
				+ "Atendimento > Requerimentos > novo > monografia/TCC/cola��o/diploma > atualizar documentos para cola��o de grau/Atualiza��o de documentos cola��o");
		nivel01.add(duvida);
		duvida = new FAQ(GRADE_ID, 5, VISUALIZAR_GRADE_2020_2,
				"https://docs.google.com/forms/d/1KQfvecNVKvP5gAzbLmp0lRYDf814k4K9ORuA4HK938M/edit");
		nivel01.add(duvida);

		duvida = new FAQ(GRADE_ID, 6, AJUSTAR_GRADE,
				"\nAten��o: <b>Atualizar</b> o valor do boleto na ABA Financeiro.\nhttp://renova.estacio.br");
		nivel01.add(duvida);

		return nivel01;
	}

	public List<FAQ> carregarNivelBiblioteca() {
		List<FAQ> nivel01 = new ArrayList<>();
		FAQ duvida;
		String devolucao = "\nA devolu��o de livro dever� ocorrer no retorno presencial das atividades.";
		duvida = new FAQ(BIBLIOTECA_ID, 1, "Devolu��o: ", devolucao);
		nivel01.add(duvida);
		return nivel01;
	}

	public List<FAQ> carregarNivelCoordenacaoADS() {
		List<FAQ> nivel01 = new ArrayList<>();
		FAQ duvida;
		String telefone = DOUGLAS_MENDES_CEL;
		String email = DOUGLAS_MENDES_ESTACIO_BR;
		duvida = new FAQ(COORDENACAO_ADS_ID, 1, CONTATO, telefone + email);
		nivel01.add(duvida);
		return nivel01;
	}

	// novo
	public List<FAQ> carregarNivelDataImportantes() {
		List<FAQ> nivel01 = new ArrayList<>();
		FAQ duvida;
		StringBuilder datas = new StringBuilder();
		datas.append("\nSimulado AV1 (Abrange os conte�dos iniciais da disciplina. Adcionar� at� 2 pontos na AV1 :))");
		datas.append("\nValide se sua disciplina esta participando.");
		datas.append("\nINI: 18/08/2020 - FIM: 22/09/2020.");
		datas.append("\nURL: https://simulado.estacio.br/alunos/");
		datas.append("\n<b>Lan�amento dos pontos no dia 19/10/2020</b>");

		datas.append("\n\nAvalia��o Parcial: INI: 17/09/2020 - FIM: 01/11/2020");
		datas.append("\n\nAV Online: INI: 29/09/2020 - FIM: 24/11/2020");
		datas.append("\n\nAV1 Presencial: INI: 05/10/2020 - FIM: 10/10/2020");
		datas.append("\n\nAVR Nova Chance: INI: 15/10/2020 - FIM: 24/10/2020");
		datas.append("\n\nAV2 Presencial: INI: 23/11/2020 - FIM: 28/11/2020");
		datas.append("\n\nAVS Online: INI: 01/12/2020 - FIM: 13/12/2020");
		datas.append("\n\nAV3 Presencial: INI: 07/12/2020 - FIM: 12/12/2020");
		duvida = new FAQ(DATAS_IMPORTANTES_ID, 1, DATAS_IMPORTANTES, datas.toString());
		nivel01.add(duvida);
		return nivel01;
	}

}
