package br.com.bottelegram.tk4telecombot;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SetChatDescription;
import com.pengrad.telegrambot.request.SetChatTitle;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.EnderecoDTO;
import bancodedados.dto.ItemPedidoDTO;
import br.com.bottelegram.comando.brewfield.CentralComandoTelegramBrewField;
import br.com.bottelegram.comando.dto.InteracaoComando;

//https://web.telegram.org/#/im?p=@cscpr_bot
public class FluxoTelegramTK4Telcom  {
	public static final Logger logger = Logger.getLogger(FluxoTelegramTK4Telcom.class);

	TelegramBot botTelegram = new TelegramBot(CentralMensagensBrewField.TOKEN_TELEGRAM_TK4TELECOMBOT);
	int offSetAtributo = 0;

	public void iniciarChatBotTelegram() {
//		if (PS1.liberarEnvio) {
//			String idManutencaCel = "-267387347";
//			TelegramBot botTK4Telecom = new TelegramBot(CentralMensagensBrewField.TOKEN_TELEGRAM_TK4TELECOMBOT);
//			botTK4Telecom.execute(new SendMessage(idManutencaCel, getMsgTelegram()));
//			PS1.liberarEnvio = false;
//			System.out.println("Bloqueado mensagem.");
//		}
	}
}