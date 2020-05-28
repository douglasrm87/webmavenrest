package br.com.bottelegram;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

/*
Douglas Mendes:
/newbot

BotFather:
Alright, a new bot. How are we going to call it? Please choose a name for your bot.

Douglas Mendes:
CSCPRBOT

BotFather:
Good. Now let's choose a username for your bot. It must end in `bot`. Like this, for example: TetrisBot or tetris_bot.

Douglas Mendes:
cscpr_bot

BotFather:
Done! Congratulations on your new bot. You will find it at t.me/cscpr_bot. You can now add a description, about section and profile picture for your bot, see /help for a list of commands. By the way, when you've finished creating your cool bot, ping our Bot Support if you want a better username for it. Just make sure the bot is fully operational before you do this.

Use this token to access the HTTP API:
1015053732:AAHWzTrMTCCSEmjoFELpVT8XYcbOQH6dvB4
Keep your token secure and store it safely, it can be used by anyone to control your bot.

For a description of the Bot API, see this page: https://core.telegram.org/bots/api
 */
public class PrincipalTelegram {
	private static final String TOKEN_TELEGRAM_PUBLICADOR = "1015053732:AAHWzTrMTCCSEmjoFELpVT8XYcbOQH6dvB4";

	public static void main(String[] args) {
		new PrincipalTelegram().processar();
	}

	private void processar() {

//		//Criação do objeto bot com as informações de acesso
		// this.bot = new TelegramBot(this.token);
		TelegramBot bot = new TelegramBot(TOKEN_TELEGRAM_PUBLICADOR);
//		//objeto responsável por receber as mensagens
		GetUpdatesResponse updatesResponse;
//		//objeto responsável por gerenciar o envio de respostas
		SendResponse sendResponse;
//		//objeto responsável por gerenciar o envio de ações do chat
		BaseResponse baseResponse;

		// controle de off-set, isto é, a partir deste ID será lido as mensagens
		// pendentes na fila
		int m = 0;

		// loop infinito pode ser alterado por algum timer de intervalo curto
		while (true) {

			// executa comando no Telegram para obter as mensagens pendentes a partir de um
			// off-set (limite inicial)
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			// lista de mensagens
			List<Update> updates = updatesResponse.updates();

			// análise de cada ação da mensagem
			if (updates != null) {
				for (Update update : updates) {

					// atualização do off-set
					m = update.updateId() + 1;

					System.out.println("Recebendo mensagem:" + update.message().text());

					// envio de "Escrevendo" antes de enviar a resposta
					baseResponse = bot
							.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
					// verificação de ação de chat foi enviada com sucesso
					System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

					// envio da mensagem de resposta
					sendResponse = bot.execute(
							new SendMessage(update.message().chat().id(), "ChatBOT CSCPR Em construção - teste01.."));
					// verificação de mensagem enviada com sucesso
					System.out.println("Mensagem Enviada?" + sendResponse.isOk());

				}
			}
		}

	}

}
