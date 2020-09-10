package br.com.bottelegram.comando.faculdade;

import java.util.List;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

import br.com.bottelegram.FluxoTelegram;

public class MenuGraficoFaculdade extends FluxoTelegram {

	// botoes de baixo com os estilos da cerveja.
	public void botoesNivel02(long idUsuario, List<FAQ> faq) {
		String vetor[] = null;
		String vetorMacro[][] = new String[faq.size()][2];

		if ((faq.size() % 2) == 0) {
			vetorMacro = new String[faq.size() / 2][2];
		} else {
			vetorMacro = new String[(faq.size() / 2) + 1][2];
		}
		int cont = 0;
		// **********************
		String add1[] = new String[faq.size()];
		for (int i = 0; i < faq.size(); i++) {
			add1[i] = faq.get(i).getIdPai() + "-" + faq.get(i).getIdItem() + "-" + faq.get(i).getDescItem();
		}
		for (int i = 0; i < (add1.length - 1); i += 2) {
			vetor = new String[] { add1[i], add1[i + 1] };
			vetorMacro[cont][0] = vetor[0];
			vetorMacro[cont][1] = vetor[1];
			cont++;
		}
		if (add1.length % 2 == 1) {
			vetor = new String[] { add1[add1.length - 1] };
			vetorMacro[cont][0] = vetor[0];
			vetorMacro[cont][1] = "";
		}
		Keyboard tecladoDinamico = new ReplyKeyboardMarkup(vetorMacro);

		SendMessage msgTelegram = new SendMessage(idUsuario, "Esclareça sua dúvida").parseMode(ParseMode.HTML)
				.disableWebPagePreview(true).replyMarkup(tecladoDinamico);
		this.botTelegram.execute(msgTelegram);
	}

	public void zerarBotoesBaixo(long idUsuario) {
		KeyboardButton numeroCelular = new KeyboardButton(".").requestContact(false);
		KeyboardButton[] vetorBotoes = { numeroCelular };
		Keyboard teclado = new ReplyKeyboardMarkup(vetorBotoes).oneTimeKeyboard(true).resizeKeyboard(true)
				.selective(true);
		SendMessage msgTelegram = new SendMessage(idUsuario, ".").parseMode(ParseMode.HTML).disableWebPagePreview(false)
				.replyMarkup(teclado);
		this.botTelegram.execute(msgTelegram);
	}

	public void menuNivel01(long idUsuario) {

		FaqEstacio faq = new FaqEstacio();
		List<FAQ> listaMenuSuperior = faq.carregarNivel01();

		InlineKeyboardButton academico = new InlineKeyboardButton(listaMenuSuperior.get(0).getDescItem())
				.callbackData(String.valueOf(listaMenuSuperior.get(0).getIdPai() + "-" + listaMenuSuperior.get(0).getIdItem()));
		InlineKeyboardButton secretaria = new InlineKeyboardButton(listaMenuSuperior.get(1).getDescItem())
				.callbackData(String.valueOf(listaMenuSuperior.get(1).getIdPai() + "-" + listaMenuSuperior.get(1).getIdItem()));
		InlineKeyboardButton financeiro = new InlineKeyboardButton(listaMenuSuperior.get(2).getDescItem())
				.callbackData(String.valueOf(listaMenuSuperior.get(2).getIdPai() + "-" + listaMenuSuperior.get(2).getIdItem()));
		InlineKeyboardButton boleto = new InlineKeyboardButton(listaMenuSuperior.get(3).getDescItem())
				.callbackData(String.valueOf(listaMenuSuperior.get(3).getIdPai() + "-" + listaMenuSuperior.get(3).getIdItem()));
		InlineKeyboardButton grade = new InlineKeyboardButton(listaMenuSuperior.get(4).getDescItem())
				.callbackData(String.valueOf(listaMenuSuperior.get(4).getIdPai() + "-" + listaMenuSuperior.get(4).getIdItem()));
		InlineKeyboardButton biblioteca = new InlineKeyboardButton(listaMenuSuperior.get(5).getDescItem())
				.callbackData(String.valueOf(listaMenuSuperior.get(5).getIdPai() + "-" + listaMenuSuperior.get(5).getIdItem()));
		InlineKeyboardButton coordencaoADS = new InlineKeyboardButton(listaMenuSuperior.get(6).getDescItem())
				.callbackData(String.valueOf(listaMenuSuperior.get(6).getIdPai() + "-" + listaMenuSuperior.get(6).getIdItem()));
		InlineKeyboardButton datasImportantes = new InlineKeyboardButton(listaMenuSuperior.get(7).getDescItem())
				.callbackData(String.valueOf(listaMenuSuperior.get(7).getIdPai() + "-" + listaMenuSuperior.get(7).getIdItem()));
		
		// novo 06 - Empregos
		InlineKeyboardButton empregos = new InlineKeyboardButton(listaMenuSuperior.get(8).getDescItem())
				.callbackData(String.valueOf(listaMenuSuperior.get(8).getIdPai() + "-" + listaMenuSuperior.get(8).getIdItem()));

		
		InlineKeyboardButton[][] vetorBotoes = { { academico, secretaria }, { financeiro, boleto },
				{ grade, biblioteca }, { coordencaoADS, datasImportantes },{empregos} }; // novo 07
		Keyboard teclado = new InlineKeyboardMarkup(vetorBotoes);

		SendMessage msgTelegram = new SendMessage(idUsuario, "<b>COOrdBOT </b> MENU PRINCIPAL").parseMode(ParseMode.HTML)
				.disableWebPagePreview(false).replyMarkup(teclado);
		this.botTelegram.execute(msgTelegram);
	}

}
