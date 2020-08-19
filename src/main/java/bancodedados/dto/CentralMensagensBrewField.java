package bancodedados.dto;

public abstract class CentralMensagensBrewField {
	private CentralMensagensBrewField() {
	}

	public static final String ESCOLHA_SEU_GROWLER = "Escolha seu Growler";
	public static final String ESCOLHA_PAGAMENTO_MENU_ABAIXO = "Escolha sua forma de pagamento entre as opções no menu abaixo.";
	public static final String FALTOU_ESCOLHER_PAGAMENTO = "Opa, faltou escolher sua forma de pagamento!";
	public static final String ADD = "ADD";
	public static final String REM = "SUB";
	public static final int ABERTO = 0;// pedido aberto
	public static final int FECHADO = 1;// pedido fechado
	public static final long CPF_PADRAO = 11111111111l;
	public static final long ID_BREW_FIELD = 1l;
	public static final String DESCRICAO_CERVEJAS_BOTAO = "Nossas cervejas na WEB.";
	public static final String MENU_PRINCIPAL = "Menu Principal";
	public static final String LINK_DESCRICAO_CERVEJAS = "https://www.goomer.app/brew-field-microcervejaria/menu";
	public static final String MEU_CELULAR = "Meu Celular";
	public static final String PRECISO_CADASTRAR_SEU_CELULAR = " <b>preciso cadastrar seu celular. Abaixo, Clique no botão "
			+ MEU_CELULAR + " e presssione Compartilhar.</b>";
	public static final String CEP_NAO_LOCALIZADO = " - CEP não localizado.\n";
	public static final String ESPACO_TRACO = " - ";
	public static final String DIVISAO_TRACO_INICIO = "_________________ Inicio:";
	public static final String DIVISAO_TRACO = "_________________";
	public static final String DIVISAO = "\n======\n";
	public static final String EXCLUSIVO_DA_CERVEJARIA_BREW_FIELD = "Este Chatbot é exclusivo da cervejaria Brew Field ";
	public static final String CERVEJARIA_BREW_FIELD = "Cervejaria Brew Field";
	public static final String PEDIDO_FINALIZADO = "\n\nPedido finalizado, aguarde nosso contato.\n";
	public static final String ITEM_NAO_CADASTRADO = "Item não cadastrado.";
	public static final int ID_TRANSFERENCIA_BANCARIA = 1;
	public static final int ID_PICPAY = 2;
	public static final int ID_CARTAO_DEBITO = 3;
	public static final int ID_CARTAO_CREDITO = 4;
	public static final int ID_ANDROID_PAY = 5;
	public static final int VALOR_INICIAL_FORMA_PAG = 0;
	public static final String AGORA_SO_FALTA_CONFIRMAR_SEU_PEDIDO = "\nAgora só falta <b>Confirmar</b> seu pedido no Menu Principal.";
	public static final String ENVIAR_SEU_COMPROVANTE_PARA_MIM = " e enviar seu comprovante para mim.\n\n";
	public static final String TOTAL_PARCIAL_DO_CARRINHO = "Total parcial do carrinho: ";
	public static final String MSG_TAXA = "Taxa R$ ";
	public static final int NUMERO_MINIMO_GROWLER = 2;
	public static final String MSG_NUMERO_MINIMO_GROWLER = "\n\nOs pedidos tem um limite minimo de "
			+ NUMERO_MINIMO_GROWLER + " Growlers. Escolha a opção <b>Selecionar Growler.</b>\n";
	public static final String DADOS_TRANSF_BANCARIA = "Banco Inter SA - 077  Agência. 0001  C/C 1415691-1\nBrew Field Microcervejaria";
	public static final String DADOS_ENVIADO_A_CERVEJARIA_BREW_FIELD = "\nDados enviado a Cervejaria Brew Field. \n";
	public static final String ESPACO = " ";
	public static final String OPCOES_PAGAMENTO_DISPONIVEIS = "Opções para pagamento. Salvar a imagem do pagamento.\n";
	public static final String DADOS_PEDIDO_REALIZADO = "<b>Pedido realizado, aguarde a entrega e divirta-se.</b>";
	public static final String RECIBO_INVALIDO_CONTACTAR_BREW_FIELD = "Recibo inválido. Contactar Brew Field.";
	public static final String SUPORTE = "Acionar suporte. 041 9 92619070";
	public static final String URL_RECIBO = "Imagem do comprovante de pagamento: ";

	public static final String BREW_FIELD_PNG = "brewField.png";
	public static final String CLARA_COPO_GROWLER = "Clara-copo-growler.jpg";
	public static final String ESCURA_COPO_GROWLER = "Escura-copo-growler.jpg";
	public static final String FORTE_COPO_GROWLER = "Forte-copo-growler.jpg";
	public static final String JOIA = "joia.jpg";

	public static final String VALOR_APOS_DO_BONUS = "Valor apos do bônus: ";
	public static final String VALOR_ANTES_DO_BONUS = "Valor antes do bônus: ";
	public static final String BONUS_CONSUMIDO = "\n Bônus consumido. \n";

	public static final String POSSUI_BONUS = "Ops! Observei que tem um bônus que da direito a um growler.\n Caso deseje posso remover o Growler de menor valor.\n\n ";
	public static final String BN = "BN";
	public static final String CONSUMIR_BONUS = BN + " - Consumir bônus.\n\n";
	public static final String TOTAL_DE_BONUS = "\n\nTotal de bônus: ";

	public static final String AGUARDE_CONFIRMACAO_PAGAMENTO = "\nAguardaremos a confirmação bancária.\n";
	public static final String TOTAL_DE_PEDIDOS = "\nTotal do pedido: ";
	public static final long ID_GRUPO_TELEGRAM = -376439227; // grupo Cervejaria Brew Field
	public static final String RECIBO_RECEBIDO_AVALIAR = "OK, recebi seu comprovante. Pedido conclusão.";
	public static final String AGUARDO_COMPROVANTE = "\n\nAguardo, por aqui mesmo a imagem de comprovante de pagamento. Pode arrastar aqui. Cheers.";
	public static final double LIMIAR_TAXA_ENTREGA = 100.00;
	public static final double TAXA_ENTREGA_MAIOR_100 = 0.00;
	public static final double TAXA_ENTREGA_MENOR_100 = 10.0;
	public static final String MSG_TAXA_ENTREGA = "\nTaxa de entrega de 10,00 para pedidos menores de R$ "
			+ LIMIAR_TAXA_ENTREGA;
	public static final String TOKEN_TELEGRAM_FACULDADE = "1015053732:AAHWzTrMTCCSEmjoFELpVT8XYcbOQH6dvB4";
	public static final String TOKEN_TELEGRAM_TK4TELECOMBOT = "1149945394:AAG9ioUI_902oqfXMrDZf27C4uvsjaTYL3U";

	public static final String SITE_CEP = "http://cep.republicavirtual.com.br/web_cep.php?cep=%s&formato=xml";

	public static final String CEP_FORMATO_INVALIDO = "CEP inválido. Digitar no formato: 80000333.";
	public static final String CEP_NAO_CURITIBA = "CEP não pertence a Curitiba-PR. CEPs válidos: de 80000 a 82999.";

	public static final String SAUDACAO_FECHAMENTO = ", confirme seu pedido, selecionando forma de pagamento ou continue comprando.\n\n";
	public static final String BREW_FIELD = "<b>Brew Field!</b>";
	public static final String SAUDACAO = "\nBem vindo ao canal automatizado de pedidos da " + BREW_FIELD;
	public static final String FUNCOES = "\nFavor seguir os passos, escolhendo os números e letras para efetuar o seu pedido. Solicitações até quinta-feira as 19h. As entregas serão na sexta no período da tarde. Pedido mínimo: "
			+ NUMERO_MINIMO_GROWLER + " Growlers.\n\n";
	public static final String MSG_FECHAMENTO = "\nAs entregas serão sempre na sexta-feira período da tarde. Caso sexta-feira seja feriado seguimos na mesma regra para quinta-feira. :)).\n";
	public static final String BOM_DIA = "\nBom dia ";
	public static final String BOA_TARDE = "\nBoa tarde ";
	public static final String BOA_NOITE = "\nBoa noite ";

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
	public static final String CELULAR_CPF_CEP_NUMERP_COMPL = "CEP,NR e COMPL";

	public static final String ENDERECO_DE_ENTREGA = "Endereço de entrega: ";

	public static final String INFORMAR_MATRICULA = "Informar a sua matricula (c0xxxxx)";

	public static final String LOGIN_RESULT = "\nAutorizado a usar o sistema.\n";

	public static final String DADOS_CADASTRAISREMOVIDOS = "Seu endereço será alterado.\n\n";
	public static final String REALIZAR_LOGIN_E_ESCOLHER_SEU_GROWLER = "Realizar Login e escolher seu Growler.";
	public static final String BLZ = "\nBlz ";
	public static final String VALOR_DO_PEDIDO_RS = "\nValor do pedido: R$ ";
	public static final String SEM_TAXA_ENTREGA = "Sem taxa de entrega.";
	public static final int ID_LOGIN = 1;
	public static final String EXEMPLO_CEP = "\nDigitar sem traços o CEP<b> , </b>NR COMPL. Usar <b>,</b> para separar. \nEX: 80050300<b> , </b>1974 ap.201.\n";
	public static final String FORMATO_INCORRETO = "Formato incorreto. Observar o exemplo apresentado.\n\n";
	public static final String VOU_TE_CADASTRAR_DIGITE_NO_FORMATO = EXEMPLO_CEP;
	public static final int ID_ALTERAR_DADOS = 8;
	public static final String ALTERAR_DADOS = "Alterar endereço";

	public static final int ID_ADICIONAR_PEDIDO = 2;
	public static final String ADICIONAR_PEDIDO = "Selecionar Growler";
	public static final String COMECE_A_COMPRAR = "Ops! Carrinho vazio. Clicar em " + ADICIONAR_PEDIDO;

	public static final int ID_VER_CARRINHO = 3;
	public static final String VER_CARRINHO = "Carrinho";

	public static final int ID_SELECIONAR_PAGAMENTO = 5;
	public static final String SELECIONAR_PAGAMENTO = "Pagamento";

	public static final int ID_CONFIRMAR_PEDIDO = 7;
	public static final String CONFIRMAR_PEDIDO = "Confirmar";

	public static final String TRANSF_BANCARIA = "Transferência bancária";
	public static final String PICPAY = "Picpay (@douglas.mendes4x4)";
	public static final String CARTAO_DEBITO = "Cartão Débito";
	public static final String LINK_ASAAS_CARTAO_CREDITO = "Use este link para pagar.\nhttps://www.asaas.com/c/133428192464";
	public static final String CARTAO_CREDITO = "Cartão Crédito";

	public static final String ESCOLHA_UM_DOS_ESTILOS_DISPONIVEIS = "Escolha um entre os %s estilos disponíveis.\nGrowlers Pet descartáveis  de 1 Litro.\n\n";
	public static final String ITEM_ADICIONADO_AO_CARRINHO = "Estilo adicionado: ";
	public static final String ITEM_NAO_ADD = "\n<b>Estilo não consta no carrinho.</b>\n";
	public static final String ITENS_DO_CARRINHO = "Itens Carrinho: \n";
	public static final String QUANTIDADE_ESCOLHIDA = "Quantidade escolhida: ";

	public static final String LAGER = "LAGER";
	public static final double VALOR_PILSEN = 12.0;
	public static final String GELA_GOELA = "Pilsen Gela Goela. R$ ";
	public static final String PILSEN = LAGER + " - " + GELA_GOELA + VALOR_PILSEN + PULAR_LINHA;

	public static final String AP = "AP";
	public static final double VALOR_APA = 16.0;
	public static final String BREAK_DOWN_APA = "Break Down APA. R$ ";
	public static final String APA = AP + ESPACO_TRACO + BREAK_DOWN_APA + VALOR_APA + PULAR_LINHA;

	public static final String IP = "IP";
	public static final String REAL = "-R$ ";

	public static final String PT = "PT";
	public static final String RD = "RD";
	public static final String INFORMAR_CLIENTE_ENDERECO_CADASTRADO = "Observei que já comprou conosco. Escolha sua opção no <b>Menu Principal</b>.";
	public static final String INFORMADO_TELEFONE = "Observei que já informou seu telefone. Só falta seu endereço. "
			+ EXEMPLO_CEP;

	public static final String[] vetSomenteLogin = { EXEMPLO_CEP };
	public static final String[] vetOpcoesParcial = { FUNCOES, ADICIONAR_PEDIDO };
	public static final String[] vetOpcoesCompleto = { ADICIONAR_PEDIDO, VER_CARRINHO, SELECIONAR_PAGAMENTO };

	public static final int[] vetMenuPrincipalOpcoesValidas = { ID_ADICIONAR_PEDIDO, ID_VER_CARRINHO,
			ID_SELECIONAR_PAGAMENTO, ID_CONFIRMAR_PEDIDO, ID_ALTERAR_DADOS };

}
