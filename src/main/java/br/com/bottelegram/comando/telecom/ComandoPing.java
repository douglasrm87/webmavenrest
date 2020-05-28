package br.com.bottelegram.comando.telecom;

import java.io.IOException;
import java.util.Scanner;

import br.com.bottelegram.comando.dto.DesktopLAN;

public class ComandoPing extends ComandoRede {

	private static final String TEMPO = "tempo";

	public static void main(String[] args) {
//		new ComandoPing().processar("L00000221");
		new ComandoPing().processar("P010631");
	}

	private static final String PING_4_N_1 = "ping -4 -n 1 ";

	private StringBuilder aplicaComandoPing(String comando) {
		StringBuilder resposta = new StringBuilder();
		try {
			Scanner pingar = new Scanner(Runtime.getRuntime().exec(comando).getInputStream());
			String respPing = "";
			while (pingar.hasNextLine()) {
				respPing = pingar.nextLine();
				if (respPing.contains(TEMPO)) {
					resposta.append(respPing + "\n");
				}
			}
			pingar.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		System.out.println("Resultado Ping: " + resposta);
		return resposta;
	}

	public String processar(String desktop) {
		DesktopLAN computador = localizarDesktopKEYServer(desktop);
		StringBuilder resposta = new StringBuilder();
		if (computador != null) {
			resposta.append(aplicaComandoPing(PING_4_N_1 + computador.getPat()));
			resposta.append(aplicaComandoPing(PING_4_N_1 + computador.getIpV4()));
			return resposta.toString();
		}
		resposta.append(PATRIMONIO_NAO_LOCALIZADO + desktop);
		return resposta.toString();

	}
}
