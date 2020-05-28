package bancodedados.dto;

import org.apache.log4j.Logger;

import br.com.bottelegram.FluxoTelegram;

public abstract class CentralMensagensBrewField {
	private CentralMensagensBrewField() {
	}
	public static final String PRECISO_CADASTRAR_SEU_CELULAR = " preciso cadastrar seu celular. Abaixo, Clique no botão ";
	public static final String MEU_CELULAR = "Meu Celular";

	public static final String CEP_NAO_LOCALIZADO = " - CEP não localizado.\n";
	public static final String ESPACO_TRACO = " - ";
	public static final String DIVISAO_TRACO_INICIO = "_________________ Inicio:";
	public static final String DIVISAO_TRACO = "_________________";
	public static final String DIVISAO = "\n======\n";
	public static final String ESTE_CHATBOT_É_EXCLUSIVO_DA_CERVEJARIA_BREW_FIELD = "Este Chatbot é exclusivo da cervejaria Brew Field ";
	public static final String CERVEJARIA_BREW_FIELD = "Cervejaria Brew Field";
	public static final String PEDIDO_FINALIZADO = "\n\nPedido finalizado, aguarde noso contato.\n";
	public static final String ITEM_NAO_CADASTRADO = "Item não cadastrado.";
	public static final int ID_TRANSFERENCIA_BANCARIA = 1; 
	public static final int ID_PICPAY = 2; 
	public static final int ID_CARTAO_DEBITO = 3; 
	public static final int ID_CARTAO_CREDITO= 4;
	public static final int ID_ANDROID_PAY = 5;
	public static final int VALOR_INICIAL_FORMA_PAG = 0;
	public static final String AGORA_SO_FALTA_CONFIRMAR_SEU_PEDIDO = "\nAgora só falta confirmar seu pedido na opção ";
	public static final String ENVIAR_SEU_COMPROVANTE_PARA_MIM = " e enviar seu comprovante para mim.\n\n";
	public static final String TOTAL_PARCIAL_DO_CARRINHO = "Total parcial do carrinho: ";
	public static final String MSG_TAXA = "Taxa R$ ";
	public static final int NUMERO_MINIMO_GROWLER = 2;
	public static final String MSG_NUMERO_MINIMO_GROWLER = "\n\nOs pedidos tem um limite minimo de "
			+ NUMERO_MINIMO_GROWLER + " Growlers.\n";
	public static final String DADOS_TRANSF_BANCARIA = "Banco Inter SA - 077  Agência. 0001  C/C 1415691-1\nBrew Field Microcervejaria";
	public static final String INFORMAR_JA_CADASTRADO = "Observei que já comprou conosco. Digite 2 para adicionar seu growler ou digite 8 para alterar seus dados cadastrais.\n";
	public static final String DADOS_ENVIADO_A_CERVEJARIA_BREW_FIELD = "\nDados enviado a Cervejaria Brew Field. \n";
	public static final String ESPACO = " ";
	public static final String OPCOES_PAGAMENTO_DISPONIVEIS = "Opções para pagamento. Salvar a imagem do pagamento.\n";
	public static final String DADOS_PEDIDO_REALIZADO = "Pedido realizado, aguarde a entrega e divirta-se.";
	public static final String RECIBO_INVÁLIDO_CONTACTAR_BREW_FIELD = "Recibo inválido. Contactar Brew Field.";
	public static final String SUPORTE = "Acionar suporte. 041 9 92619070";
	public static final String URL_RECIBO = "Imagem do comprovante de pagamento: ";
	public static final String BREW_FIELD_PNG = "brewField.png";
	public static final String CLARA_COPO_GROWLER = "Clara-copo-growler.jpg";
	public static final String ESCURA_COPO_GROWLER = "Escura-copo-growler.jpg";
	public static final String FORTE_COPO_GROWLER = "Forte-copo-growler.jpg";

	public static final String VALOR_APOS_DO_BONUS = "Valor apos do bônus: ";
	public static final String VALOR_ANTES_DO_BONUS = "Valor antes do bônus: ";
	public static final String BONUS_CONSUMIDO = "\n Bônus consumido. \n";

	public static final String POSSUI_BONUS = "Ops! Observei que tem um bônus que da direito a um growler.\n Caso deseje posso remover o valor do Growçler de menor preço.\n\n ";
	public static final String BN = "BN";
	public static final String CONSUMIR_BONUS = BN + " - Consumir bônus.\n\n";
	public static final String TOTAL_DE_BONUS = "\n\nTotal de bônus: ";

	public static final String AGUARDE_CONFIRMACAO_PAGAMENTO = "\nAguardaremos a confirmação bancária.\n";
	public static final String TOTAL_DE_PEDIDOS = "\nTotal do pedido: ";
	public static final long ID_GRUPO_TELEGRAM = -376439227; // grupo Cervejaria Brew Field
	public static final String RECIBO_RECEBIDO_AVALIAR = "OK, recebi seu comprovante. Pedido concluído.";
	public static final String AGUARDO_COMPROVANTE = "\n\nAguardo, por aqui mesmo a imagem de comprovante de pagamento. Pode arrastar aqui. Após confirmarei seu pedido. Cheers.";
	public static final double LIMIAR_TAXA_ENTREGA = 100.00;
	public static final double TAXA_ENTREGA_MAIOR_100 = 0.00;
	public static final double TAXA_ENTREGA_MENOR_100 = 10.0;
	public static final String MSG_TAXA_ENTREGA = "\nTaxa de entrega de 10,00 para pedidos menores de R$ "
			+ LIMIAR_TAXA_ENTREGA;
	public static final String FORMA_DE_PAGAMENTO_REMOVIDA = "Forma de pagamento removida, escolher uma nova.";
	public static final String TOKEN_TELEGRAM_FACULDADE = "1015053732:AAHWzTrMTCCSEmjoFELpVT8XYcbOQH6dvB4";
	public static final String TOKEN_TELEGRAM_TK4TELECOMBOT = "1149945394:AAG9ioUI_902oqfXMrDZf27C4uvsjaTYL3U";

	public static final String SITE_CEP = "http://cep.republicavirtual.com.br/web_cep.php?cep=%s&formato=xml";

	public static final String CEP_FORMATO_INVALIDO = "CEP inválido. Digitar no formato: 80000333.";
	public static final String CEP_NAO_CURITIBA = "CEP não pertence a Curitiba-PR. Válido entre 80000 a 82999.";

	public static final String SAUDACAO_FECHAMENTO = ", confirme seu pedido, selecionando forma de pagamento ou continue comprando.\n\n";
	public static final String SAUDACAO = "\nBem vindo ao canal automatizado de pedidos da Brew Field!";
	public static final String FUNCOES = "\nFavor seguir os passos, escolhendo os números e letras para efetuar o seu pedido. Solicitações até quinta-feira as 19h. As entregas serão na sexta no período da tarde.\n";
	public static final String MSG_FECHAMENTO = "\nAs entregas serão sempre na sexta-feira período da tarde. Caso sexta-feira seja feriado seguimos na mesma regra para quinta-feira. :)).\n";
	public static final String BOM_DIA = "\nBom dia ";
	public static final String BOA_TARDE = "\nBoa tarde ";
	public static final String BOA_NOITE = "\nBoa noite ";
	public static final String FORMATO_INCORRETO = "Dados para cadastro incorreto.\n\n";
	public static final String FECHANDO_SEU_PEDINDO = "Fechando seu pedindo.\n";
	public static final String CADASTRADO_COM_SUCESSO = "Cadastrado com sucesso.\n\n";
	public static final String PAGAMENTO_ESCOLHIDO = "\nEscolhido pagar via: ";
	public static final String ALTERAR_DADOS_CLIENTE = "AL - Alterar Dados Cliente.\n";
	public static final String AL_DADOS_CLIENTE = "AL";
	public static final String CEP = "CEP:";
	public static final String CPF = "CPF: ";
	public static final String ENDERECO = "Endereco:";
	public static final String PODERA_ALTERAR_PARA_RECADASTRAR = "\nPoderá alterar seus dados.\n";
	public static final String PULAR_LINHA = "\n";
	public static final String NOME = "Nome:";
	public static final String CELULAR = "Celular:";
	public static final String CLIENTE_JA_CADASTRADO = "Cliente já cadastrado:\n\n";
	public static final String CELULAR_CPF_CEP_NUMERP_COMPL = "(DDD+9)Celular#Cpf#Cep#Numero e Compl";
	public static final String VOU_TE_CADASTRAR_DIGITE_NO_FORMATO = "Vou te cadastrar: Digite no formato:\n";

	public static final String ENDERECO_DE_ENTREGA = "Endereço de entrega: ";

	public static final String INFORMAR_MATRICULA = "Informar a sua matricula (c0xxxxx)";

	public static final String LOGIN_RESULT = "\nAutorizado a usar o sistema.\n";

	public static final String DADOS_CADASTRAISREMOVIDOS = "Dados cadastrais removidos.\n\n";
	public static final String REALIZAR_LOGIN_E_ESCOLHER_SEU_GROWLER = "Realizar Login e escolher seu Growler.";
	public static final String BLZ = "\nBlz ";
	public static final String VALOR_DO_PEDIDO_RS = "\nValor do pedido: R$ ";
	public static final String SEM_TAXA_ENTREGA = "Sem taxa de entrega.";

	public static final int ID_LOGIN = 1;
	public static final String LOGIN = "\nDigitar CEP,COMPL. <b>Virgula para separar</b>. (EX: 80050350,427 ap.201).\n";
	public static final int ID_ALTERAR_DADOS = 8;
	public static final String ALTERAR_DADOS = ID_ALTERAR_DADOS + " - Alterar dados.\n";

	public static final int ID_ADICIONAR_PEDIDO = 2;
	public static final String ADICIONAR_PEDIDO = ID_ADICIONAR_PEDIDO + " - Adicionar Growler.\n";

	public static final int ID_VER_CARRINHO = 3;
	public static final String VER_CARRINHO = ID_VER_CARRINHO + " - Ver carrinho de compras.\n";

//	public static final int ID_CONTINUAR_COMPRANDO = 4;
//	public static final String CONTINUAR_COMPRANDO = "4 - Continuar comprando.\n";

	public static final int ID_SELECIONAR_PAGAMENTO = 5;
	public static final String SELECIONAR_PAGAMENTO = ID_SELECIONAR_PAGAMENTO + " - Selecionar Pagamento.\n";

	public static final int ID_CANCELAR_PEDIDO = 76;
	public static final String CANCELAR_PEDIDO = ID_CANCELAR_PEDIDO + " - Cancelar pedido.\n";

	public static final int ID_CONFIRMAR_PEDIDO = 7;
	public static final String CONFIRMAR_PEDIDO = ID_CONFIRMAR_PEDIDO + " - Confirmar pedido.\n";

	public static final String VOLTAR_MENU_FECHAR = " para fechar.";
	public static final String VL = "VL";
	public static final String VOLTAR_MENU = VL + " - Voltar menu";
	public static final int ID_VOLTAR = 1009;

	public static final String AL = "AL";
	public static final String ALTERAR_PAGAMENTO = AL + " - Alterar forma pagamento.\n";
	public static final String TB = "TB";
	public static final String TRANSF_BANCARIA = TB + " - Transferência bancária.\n";
	public static final String PI = "PI";
	public static final String PICPAY = PI + " - Picpay (@jeann.s).\n";
	public static final String CD = "CD";
	public static final String CARTAO_DEBITO = CD + " - Cartão débito.\n";
	public static final String CC = "CC";
	public static final String CARTAO_CREDITO = CC + " - Cartão crédito.\n";
	public static final String AN = "AN";
	public static final String ANDROID_PAY = AN + " - Android Pay..\n";

	public static final String ESCOLHA_UM_DOS_ESTILOS_DISPONIVEIS = "Escolha um entre os 8 estilos disponíveis.\nGrowlers Pet descartáveis  de 1L.\n\n";
	public static final String ITEM_ADICIONADO_AO_CARRINHO = "Estilo adicionado: ";
	public static final String ITENS_DO_CARRINHO = "Itens adicionados: \n";
	public static final String QUANTIDADE_ESCOLHIDA = "Quantidade escolhida: ";

	public static final String GG = "GG";
	public static final double VALOR_PILSEN = 12.0;
	public static final String GELA_GOELA = "Pilsen Gela Goela. R$ ";
	public static final String PILSEN = GG + " - " + GELA_GOELA + VALOR_PILSEN + PULAR_LINHA;

	public static final String AP = "AP";
	public static final double VALOR_APA = 16.0;
	public static final String BREAK_DOWN_APA = "Break Down APA. R$ ";
	public static final String APA = AP + ESPACO_TRACO + BREAK_DOWN_APA + VALOR_APA + PULAR_LINHA;

	public static final String IP = "IP";
	public static final double VALOR_IPA = 18.0;
	public static final String BREAK_DOWN_IPA = "Break Down IPA. R$ ";
	public static final String IPA = IP + ESPACO_TRACO + BREAK_DOWN_IPA + VALOR_IPA + PULAR_LINHA;

	public static final String PT = "PT";
	public static final double VALOR_PORTER = 18.0;
	public static final String BLACK_SAILS = "Porter Black Sails. R$ ";
	public static final String PORTER = PT + ESPACO_TRACO + BLACK_SAILS + VALOR_PORTER + PULAR_LINHA;

	public static final String RD = "RD";
	public static final double VALOR_RED = 17.0;
	public static final String RED_ALE = "IRA Red ALE. R$ ";
	public static final String RED = RD + ESPACO_TRACO + RED_ALE + VALOR_RED + PULAR_LINHA;

	public static final String vetOpcoesBrewField[] = { PILSEN, APA, IPA, PORTER, RED,
			VOLTAR_MENU + VOLTAR_MENU_FECHAR };
	public static final String[] vetSomenteLogin = {LOGIN};
	public static final String[] vetOpcoesParcial = { FUNCOES, LOGIN, ADICIONAR_PEDIDO };
	public static final String[] vetOpcoesJaLogadoContinuar = { FUNCOES, ADICIONAR_PEDIDO };
	public static final String[] vetOpcoesUsuarioJaCadastrado = { ADICIONAR_PEDIDO, ALTERAR_DADOS };
	public static final String[] vetOpcoesCompleto = { ADICIONAR_PEDIDO, VER_CARRINHO, 	SELECIONAR_PAGAMENTO, CANCELAR_PEDIDO };
	public static final String[] vetOpcoesNoLogin = { FUNCOES, ADICIONAR_PEDIDO, ALTERAR_DADOS };
	public static final String[] vetOpcoesPagamento = { TRANSF_BANCARIA, CARTAO_DEBITO, CARTAO_CREDITO, PICPAY,
			ALTERAR_PAGAMENTO, VOLTAR_MENU };

	public static final String[] vetOpcoesAposPagamento = { VER_CARRINHO, CANCELAR_PEDIDO,
			VOLTAR_MENU };
	public static final String[] vetOpcoesFechamento = { CANCELAR_PEDIDO, CONFIRMAR_PEDIDO,  VOLTAR_MENU };

}
