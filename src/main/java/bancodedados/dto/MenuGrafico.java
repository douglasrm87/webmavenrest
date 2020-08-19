package bancodedados.dto;

import java.util.ArrayList;
import java.util.List;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.InlineQueryResult;
import com.pengrad.telegrambot.model.request.InlineQueryResultArticle;
import com.pengrad.telegrambot.model.request.InlineQueryResultGif;
import com.pengrad.telegrambot.model.request.InlineQueryResultMpeg4Gif;
import com.pengrad.telegrambot.model.request.InlineQueryResultPhoto;
import com.pengrad.telegrambot.model.request.InlineQueryResultVideo;
import com.pengrad.telegrambot.model.request.InputLocationMessageContent;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.LabeledPrice;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendInvoice;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendSticker;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.bottelegram.FluxoTelegram;

public class MenuGrafico extends FluxoTelegram {

	public static void main(String[] args) {
		new MenuGrafico().processar();

	}

	private void processar() {
		List<ProdutoBebida> listaCervejas = carregarProdutoCerveja();
		String vetor[] = null;
		String vetorMacro[][] = new String[listaCervejas.size()][2];

		if ((listaCervejas.size() % 2) == 0) {
			vetorMacro = new String[listaCervejas.size() / 2][2];
		} else {
			vetorMacro = new String[(listaCervejas.size() / 2) + 1][2];
		}
		int cont = 0;
		// **********************
		String add1[] = new String[listaCervejas.size()];
		for (int i = 0; i < listaCervejas.size(); i++) {
			add1[i] = CentralMensagensBrewField.ADD + "-" + listaCervejas.get(i).getDescricaoCurta()
					+ CentralMensagensBrewField.REAL + listaCervejas.get(i).getValorLitro();
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
		}
		for (int i = 0; i < vetorMacro.length; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(vetorMacro[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void menuOpcoesCervejaGrafico(long idUsuario) {

		KeyboardButton gg = new KeyboardButton(CentralMensagensBrewField.LAGER);
		KeyboardButton ap = new KeyboardButton(CentralMensagensBrewField.AP);
		KeyboardButton ip = new KeyboardButton(CentralMensagensBrewField.IP);
		KeyboardButton rd = new KeyboardButton(CentralMensagensBrewField.RD);
		KeyboardButton pt = new KeyboardButton(CentralMensagensBrewField.PT);

		KeyboardButton[] vetorBotoes = { gg, ap, ip, rd, pt };
		Keyboard teclado = new ReplyKeyboardMarkup(vetorBotoes).oneTimeKeyboard(true).resizeKeyboard(true)
				.selective(true);
		SendMessage msgTelegram = new SendMessage(idUsuario,
				CentralMensagensBrewField.ESCOLHA_UM_DOS_ESTILOS_DISPONIVEIS).parseMode(ParseMode.HTML)
						.disableWebPagePreview(false).replyMarkup(teclado);
		SendResponse sendResponse = this.botTelegram.execute(msgTelegram);

	}

	// precisa publico para permitir que seja carregada e seja utilizada para
	// comparação.
	public List<FormasPagamento> carregarFormasPagamento() {

		List<FormasPagamento> listaFormas = new ArrayList<>();
		FormasPagamento formas = null;
		formas = new FormasPagamento(CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA,
				CentralMensagensBrewField.TRANSF_BANCARIA);
		listaFormas.add(formas);
		formas = new FormasPagamento(CentralMensagensBrewField.ID_PICPAY, CentralMensagensBrewField.PICPAY);
		listaFormas.add(formas);
		formas = new FormasPagamento(CentralMensagensBrewField.ID_CARTAO_DEBITO,
				CentralMensagensBrewField.CARTAO_DEBITO);
		listaFormas.add(formas);
		formas = new FormasPagamento(CentralMensagensBrewField.ID_CARTAO_CREDITO,
				CentralMensagensBrewField.CARTAO_CREDITO);
		listaFormas.add(formas);
		return listaFormas;
	}

	// botoes de baixo com os estilos da cerveja.
	public void botoesFormasPAgamento(long idUsuario) {
		List<FormasPagamento> listaCervejas = carregarFormasPagamento();
		String vetor[] = null;
		String vetorMacro[][] = new String[2][2];

		String tp1 = listaCervejas.get(0).getDescPagamento();
		String tp2 = listaCervejas.get(1).getDescPagamento();
		String tp3 = listaCervejas.get(2).getDescPagamento();
		String tp4 = listaCervejas.get(3).getDescPagamento();

		vetorMacro[0][0] = tp1;
		vetorMacro[0][1] = tp2;

		vetorMacro[1][0] = tp3;
		vetorMacro[1][1] = tp4;

		Keyboard tecladoDinamico = new ReplyKeyboardMarkup(vetorMacro).resizeKeyboard(true);

		SendMessage msgTelegram = new SendMessage(idUsuario, CentralMensagensBrewField.ESCOLHA_PAGAMENTO_MENU_ABAIXO)
				.parseMode(ParseMode.HTML).disableWebPagePreview(true).replyMarkup(tecladoDinamico);
		this.botTelegram.execute(msgTelegram);

	}

	// precisa publico para permitir que seja carregada e seja utilizada para
	// comparação.
	public List<ProdutoBebida> carregarProdutoCerveja() {
		List<ProdutoBebida> listaProdutos = new ArrayList<>();
		ProdutoBebida produto = null;
		produto = new ProdutoBebida("Lager", "Pilsen", "LAGER", "Gela Goela", 4, 13, 50, "Pilsen leve e refrescante.",
				12, "Pilsen leve e refrescante");
		listaProdutos.add(produto);
		produto = new ProdutoBebida("Lager", "Munich Helles", "HANGAR", "Hangar 51", 4, 13, 50,
				"Estilo alem�o da fam�lia Lager, dourada, com malte bem presente, leve e saborosa! Levemente frutada, com suave amargor do lúpulo, entregando uma cerveja de corpo médio e equilibrada.",
				16, "Munich Helles Leve e cremosa");
		listaProdutos.add(produto);

		produto = new ProdutoBebida("Ale", "Brew Weiss", "WEISS", "Weiss", 5, 14, 50,
				"Chopp de trigo leve com os aromas tradicionais de cravo e banana.", 16,
				"Leve, notas de cravo e banana");
		listaProdutos.add(produto);
		produto = new ProdutoBebida("Ale", "American Pale Ale", "DAPA", "Double APA 4x4", 6.3, 55, 50,
				"Pale Ale carregada, um estilo americano com maior amargor, médio corpo e mais alcoolica.", 18,
				"Encorpada e refrescante");
		listaProdutos.add(produto);

		produto = new ProdutoBebida("Ale", "Indian Pale Ale", "BDIPA", "IPA Break Down", 5.6, 50, 50,
				"O intenso aroma e amargor do lúpulo tomarão conta dos seus sentidos, com notas cítricas e florais.",
				18, "Encorpada");
		listaProdutos.add(produto);

		produto = new ProdutoBebida("Ale", "Red Ale", "IRA", "Red Ale IRA", 4.8, 23, 50,
				"Refrescante, com um corpo equilibrado e sua característica cor avermelhada apresenta o típico sabor do malte com notas sutis de cereais torrados e amanteigados, com leve presença do lúpulo em segundo plano e final seco.",
				18, "Leve, notas de caramelo");
		listaProdutos.add(produto);

		produto = new ProdutoBebida("Ale", "Cacau Porter", "PORTER", "Porter Black Sails", 4.7, 22, 50,
				"Esta combinação de maltes proporciona um aroma tostado remetendo a toffee e cacau. No sabor um equilíbrio entre o dulçor e o amargor, com um corpo médio-baixo.",
				18, "Notas de Cacau");
		listaProdutos.add(produto);

		produto = new ProdutoBebida("Ale", "Double IPA ", "DIPA", "Double IPA Apocalipse", 7.5, 77, 50,
				"Nossa receita Imperial IPA possui personalidade forte e marcante, carregada com muito lúpulo americano e do novo mundo, proporcionando uma explosão de sabores, aromas e amargor intenso sem renegar a presença dos maltes especiais. Cerveja encorpada e de cor cobreada.",
				20, "Lupulada e arom�tica");
		listaProdutos.add(produto);

		return listaProdutos;
	}

	// botoes de baixo com os estilos da cerveja.
	public void botoesEstilosCervejasLinhasDin(long idUsuario, String tpBotao) {
		List<ProdutoBebida> listaCervejas = carregarProdutoCerveja();
		String vetor[] = null;
		String vetorMacro[][] = new String[listaCervejas.size()][2];

		if ((listaCervejas.size() % 2) == 0) {
			vetorMacro = new String[listaCervejas.size() / 2][2];
		} else {
			vetorMacro = new String[(listaCervejas.size() / 2) + 1][2];
		}
		int cont = 0;
		// **********************
		String add1[] = new String[listaCervejas.size()];
		for (int i = 0; i < listaCervejas.size(); i++) {
			add1[i] = tpBotao + "-" + listaCervejas.get(i).getDescricaoCurta() + CentralMensagensBrewField.REAL
					+ listaCervejas.get(i).getValorLitro() + " " + listaCervejas.get(i).getDescricaoMedia();
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

		SendMessage msgTelegram = new SendMessage(idUsuario, CentralMensagensBrewField.ESCOLHA_SEU_GROWLER)
				.parseMode(ParseMode.HTML).disableWebPagePreview(true).replyMarkup(tecladoDinamico);
		this.botTelegram.execute(msgTelegram);
	}

	public void zerarBotoesBaixo(long idUsuario) {
		KeyboardButton numeroCelular = new KeyboardButton(".").requestContact(false);
		KeyboardButton[] vetorBotoes = { numeroCelular };
		Keyboard teclado = new ReplyKeyboardMarkup(vetorBotoes).oneTimeKeyboard(true).resizeKeyboard(true)
				.selective(true);
		SendMessage msgTelegram = new SendMessage(idUsuario, ".").parseMode(ParseMode.HTML).disableWebPagePreview(false)
				.replyMarkup(teclado);
		SendResponse sendResponse = this.botTelegram.execute(msgTelegram);
	}

	public void menulinkCervejas(long idUsuario) {
		InlineKeyboardButton linkDescCervejas = new InlineKeyboardButton(
				CentralMensagensBrewField.DESCRICAO_CERVEJAS_BOTAO)
						.url(CentralMensagensBrewField.LINK_DESCRICAO_CERVEJAS);

		InlineKeyboardButton[] vetorBotoes = { linkDescCervejas };
		Keyboard teclado = new InlineKeyboardMarkup(vetorBotoes);

		SendMessage msgTelegram = new SendMessage(idUsuario, CentralMensagensBrewField.DESCRICAO_CERVEJAS_BOTAO)
				.parseMode(ParseMode.HTML).disableWebPagePreview(false).replyMarkup(teclado);
		SendResponse sendResponse = this.botTelegram.execute(msgTelegram);
	}

	private void menuOpcoesCervejaGraficoDinamico(long idUsuario) {
		List<ProdutoBebida> listaProdutos = carregarProdutoCerveja();
		int qdadeItens = listaProdutos.size();
		KeyboardButton vetorBotoes[] = new KeyboardButton[qdadeItens];

		for (int i = 0; i < listaProdutos.size(); i++) {
			vetorBotoes[i] = new KeyboardButton(listaProdutos.get(i).getSigla());
		}
		Keyboard teclado = new ReplyKeyboardMarkup(vetorBotoes).oneTimeKeyboard(true).resizeKeyboard(true)
				.selective(true);
		String msg = String.format(CentralMensagensBrewField.ESCOLHA_UM_DOS_ESTILOS_DISPONIVEIS, listaProdutos.size());
		SendMessage msgTelegram = new SendMessage(idUsuario, msg).parseMode(ParseMode.HTML).disableWebPagePreview(false)
				.replyMarkup(teclado);
		this.botTelegram.execute(msgTelegram);

	}

	public void menuOpcoesGrafico(long idUsuario) {
		InlineKeyboardButton addCervejas = new InlineKeyboardButton(CentralMensagensBrewField.ADICIONAR_PEDIDO)
				.callbackData(String.valueOf(CentralMensagensBrewField.ID_ADICIONAR_PEDIDO));
		InlineKeyboardButton verCarrinho = new InlineKeyboardButton(CentralMensagensBrewField.VER_CARRINHO)
				.callbackData(String.valueOf(CentralMensagensBrewField.ID_VER_CARRINHO));
		InlineKeyboardButton selPagamento = new InlineKeyboardButton(CentralMensagensBrewField.SELECIONAR_PAGAMENTO)
				.callbackData(String.valueOf(CentralMensagensBrewField.ID_SELECIONAR_PAGAMENTO));
		InlineKeyboardButton confPedido = new InlineKeyboardButton(CentralMensagensBrewField.CONFIRMAR_PEDIDO)
				.callbackData(String.valueOf(CentralMensagensBrewField.ID_CONFIRMAR_PEDIDO));
		InlineKeyboardButton altEndereco = new InlineKeyboardButton(CentralMensagensBrewField.ALTERAR_DADOS)
				.callbackData(String.valueOf(CentralMensagensBrewField.ID_ALTERAR_DADOS));

		InlineKeyboardButton linkDescCervejas = new InlineKeyboardButton(
				CentralMensagensBrewField.DESCRICAO_CERVEJAS_BOTAO)
						.url(CentralMensagensBrewField.LINK_DESCRICAO_CERVEJAS);

		InlineKeyboardButton[][] vetorBotoes = { { addCervejas, verCarrinho }, { selPagamento, confPedido },
				{ altEndereco }, { linkDescCervejas } };
		Keyboard teclado = new InlineKeyboardMarkup(vetorBotoes);

		SendMessage msgTelegram = new SendMessage(idUsuario, CentralMensagensBrewField.MENU_PRINCIPAL)
				.parseMode(ParseMode.HTML).disableWebPagePreview(false).replyMarkup(teclado);
		SendResponse sendResponse = this.botTelegram.execute(msgTelegram);
	}

	private void sendInvoice(long idUsuario, String valorSTR) {
//FormasPagamento.API_KEY_ASAAS

		valorSTR = "121.00";
		SendInvoice sendInvoice = new SendInvoice((int) idUsuario, "Pagamento Pedido", "Asaas Pagamento", "my_payload",
				"providerToken", valorSTR, "BRL", new LabeledPrice("Valores", 200)).needPhoneNumber(false)
						.needShippingAddress(true).isFlexible(false)
						.replyMarkup(new InlineKeyboardMarkup(
								new InlineKeyboardButton[] { new InlineKeyboardButton("Pague Aqui!").pay(),
										new InlineKeyboardButton("Pagar Asaas").url("https://www.asaas.com/c/133428192464") }));
		SendResponse response = this.botTelegram.execute(sendInvoice);
//		SendSticker sendSticker = new SendSticker(idUsuario, "brewField.jpg");
//		this.botTelegram.execute(sendSticker);
//		
//		InlineQueryResult r1 = new InlineQueryResultPhoto("id", "brewField.jpg", "thumbUrl");
//		InlineQueryResult r2 = new InlineQueryResultArticle("id", "title", "message text").thumbUrl("url");
//		InlineQueryResult r3 = new InlineQueryResultGif("id", "gifUrl", "thumbUrl");
//		InlineQueryResult r4 = new InlineQueryResultMpeg4Gif("id", "mpeg4Url", "thumbUrl");
//
//		InlineQueryResult r5 = new InlineQueryResultVideo(
//		  "id", "videoUrl", InlineQueryResultVideo.MIME_VIDEO_MP4, "message", "thumbUrl", "video title")
//		    .inputMessageContent(new InputLocationMessageContent(21.03f, 105.83f));

	}

}
