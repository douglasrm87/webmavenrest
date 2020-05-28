package br.com.bottelegram.comando.faculdade;

public class ComandoCallCenter extends ComandoFaculdade {

	@Override
	public String processarFaculdade(String comando) {
		StringBuilder ret = new StringBuilder();
		ret.append("\nContactar Call center\n");
		ret.append("Call Center aos alunos: 08008806767 ou 4003.6767");
		return ret.toString();
	}

}
