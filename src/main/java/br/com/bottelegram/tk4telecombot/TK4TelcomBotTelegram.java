package br.com.bottelegram.tk4telecombot;
import java.io.Serializable;
import org.apache.log4j.Logger;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

/*
		<dependency>
			<groupId>com.github.pengrad</groupId>
			<artifactId>java-telegram-bot-api</artifactId>
			<version>4.8.0</version>
		</dependency>

 */

public class TK4TelcomBotTelegram implements Serializable {
	private static final Logger logger = Logger.getLogger(TK4TelcomBotTelegram.class);
	private static final long serialVersionUID = 1L;
	public static final String idManutencaCel = "-267387347";
	public static final String TOKEN_TELEGRAM_TK4TELECOMBOT = "1149945394:AAG9ioUI_902oqfXMrDZf27C4uvsjaTYL3U";
	
	public void enviarMensagemTelegram(String mensagemPS1) {
		TelegramBot botTK4Telecom = new TelegramBot(TOKEN_TELEGRAM_TK4TELECOMBOT);
		botTK4Telecom.execute(new SendMessage(idManutencaCel, mensagemPS1));
		System.out.println("Mensagem enviada: " + mensagemPS1);
	}
}
