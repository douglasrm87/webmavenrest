package br.com.bottelegram.tk4telecombot;

import java.util.Scanner;

public class PS1 {
	public static void main(String[] args) {
		new PS1().processar();
	}
	private void processar() {
		SimularPS1();
	}
	
	Scanner leia = new Scanner(System.in).useDelimiter("\r\n");
	protected void SimularPS1() {
		int op = 0;
		TK4TelcomBotTelegram telegram = new TK4TelcomBotTelegram();
		while (op != 9) {
			System.out.println("1 - Enviar mensagem.");
			System.out.println("9 - Sair.");
			op = this.leia.nextInt();
			switch (op) {
			case 1:
				System.out.println("Digite sua mensagem:");
				String msgTelegram  = this.leia.next();
				telegram.enviarMensagemTelegram(msgTelegram);
				break;
			case 9:
				System.out.println("saindo.");
				break;

			default:
				break;
			}
		}
	}
}
