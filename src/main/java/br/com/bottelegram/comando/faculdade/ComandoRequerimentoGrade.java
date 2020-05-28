package br.com.bottelegram.comando.faculdade;

public class ComandoRequerimentoGrade extends ComandoFaculdade {

	@Override
	public String processarFaculdade(String comando) {
		StringBuilder ret = new StringBuilder();
		ret.append("\nProcedimento para abrir requerimento ajuste grade:\n");
		ret.append("SIA - Requerimento - novo - currícular - aula/horários - solicitar alteração de grade");
		return ret.toString();
	}

}
