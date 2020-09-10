package bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

//http://brunorota.com.br/2012/05/19/tutorial-criar-crud-em-java-com-jdbc-parte-1-final/

// senha no pgadmin - 123456
// pgadmin: http://127.0.0.1:54654/browser/#

public class PostgreSQLJDBC {
	private static final String ORG_POSTGRESQL_DRIVER = "org.postgresql.Driver";
	
	private static final String LOCAL_POSTGREE = "jdbc:postgresql://localhost:5432/";
	private static final String USUARIO = "postgres";
	private static final String SENHA_BD = "123456";
	private static final String DATABASE = "faculdade";

	
	private static final String PORTA_HEROKU = "5432/";
	private static final String HOST_HEROKU = "jdbc:postgresql://ec2-34-225-82-212.compute-1.amazonaws.com:" + PORTA_HEROKU;
	private static final String USUARIO_HEROKU = "jrmnzjfkazhnfx";
	private static final String DATABASE_HEROKU = "d18rq99epvib6";
	private static final String SENHA_HEROKU = "476677037096d7eb2110bb22238fa0da06b5ba76e1e5c7861f28927c9c9d74ef";
	
	
	protected static final String TABELA_CARTAO_FIDELIDADE = " CARTAO_FIDELIDADE ";
	protected static final String TABELA_BONUS_CARTAO_FIDELIDADE = " BONUS_CARTAO_FIDELIDADE ";
	protected static final String ID_CARTAO = "id_cartao";// não permite espaços.
	protected static final String ID_BONUS = "id_bonus";// não permite espaços.
	protected static final String ESTADO_SELO_CARTAO = "estado_selo_cartao";
	protected static final String DATA_INCLUSAO = "data_inclusao";// não permite espaços.
	protected static final String DATA_VENCIMENTO = "data_vencimento";// não permite espaços.

	protected static final String BONUS_CONSUMIDO = "bonus_consumido";
	protected static final String BONUS_ATIVADO = "bonus_Ativado";

	protected static final String TABELA_CLIENTE_DTO = " CLIENTE ";
	protected static final String TABELA_ENDERECO_DTO = " ENDERECO ";
	protected static final String TABELA_PEDIDO_DTO = " PEDIDO ";
	protected static final String TABELA_ITEM_PEDIDO_DTO = " ITEM_PEDIDO ";
	protected static final String TABELA_PAGAMENTO_DTO = " PAGAMENTO ";
	protected static final String ID_PEDIDO = "id_pedido";// não permite espaços.
	protected static final String ID_PAGAMENTO = "id_pagamento";// não permite espaços.
	protected static final String ID_ITEM_PEDIDO = "id_item_pedido";// não permite espaços.
	protected static final String ID_CLIENTE = "id_cliente";// não permite espaços.
	protected static final String CPF_CLIENTE = "cpf_cliente";// não permite espaços.
	protected static final String NOME = "nome";
	protected static final String DATA_REGISTRO = "data_registro";
	protected static final String ID_FORMA_PAGAMENTO = "id_forma_pagamento";
	protected static final String DESCRICAO_ESTILO = "descricao_estilo";
	protected static final String URL_RECIBO = "url_recibo";
	protected static final String VALOR_PEDIDO = "valor_total_pedido";
	protected static final String VALOR_ITEM_PEDIDO = "valor_item_pedido";
	protected static final String ESTADO_PEDIDO = "estado_pedido";
	protected static final String AND = " AND ";
	protected static final String VIRGULA = " , ";

	protected static final String PERMITE_EMAIL_SMS = "permite_email_sms";
	protected static final String EMAIL = "email";
	protected static final String EMPRESA = "empresa";
	protected static final String TELEFONE = "telefone";
	protected static final String ID_USUARIO_TELEGRAM = "id_usuario_telegram";
	protected static final String CEP = "cep";

	protected static final String UF = "uf";
	protected static final String CIDADE = "cidade";
	protected static final String BAIRRO = "bairro";
	protected static final String TIPO_LOGRADOURO = "tipo_logradouro";
	protected static final String LOGRADOURO = "logradouro";
	protected static final String NUMERO = "numero";

	protected static final String FECHA_PARENTESES = " ) ";
	protected static final String ABRE_PARENTESES = " ( ";
	protected Scanner leia = new Scanner(System.in);

	public static void main(String[] args) {
		PostgreSQLJDBC p = new PostgreSQLJDBC();
		Connection c = p.conectarBDPostgree();
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String iswindows() {
		// So: Windows 7
		String so = System.getProperty("os.name");
		System.out.println("So: " + so);
		return so;
	}

//	Host:    	ec2-34-225-82-212.compute-1.amazonaws.com
//	Database: 	d18rq99epvib6
//	User:		jrmnzjfkazhnfx
//	Port:		5432
//	Password: 	476677037096d7eb2110bb22238fa0da06b5ba76e1e5c7861f28927c9c9d74ef
//	URI:		postgres://jrmnzjfkazhnfx:476677037096d7eb2110bb22238fa0da06b5ba76e1e5c7861f28927c9c9d74ef@ec2-34-225-82-212.compute-1.amazonaws.com:5432/d18rq99epvib6
//	Heroku CLI: 	heroku pg:psql postgresql-metric-49187 --app webmavenheroku
	protected Connection conectarBDPostgree() {
		Connection con = null;
		try {
			Class.forName(ORG_POSTGRESQL_DRIVER);
			if (!iswindows().contains("Linux")) {
				con = DriverManager.getConnection(LOCAL_POSTGREE + DATABASE, USUARIO, SENHA_BD);
				return con;
			} else {
				// Usar dados do Heroku
//				URI:		postgres://jrmnzjfkazhnfx:476677037096d7eb2110bb22238fa0da06b5ba76e1e5c7861f28927c9c9d74ef@ec2-34-225-82-212.compute-1.amazonaws.com:5432/d18rq99epvib6
//				Heroku CLI: 	heroku pg:psql postgresql-metric-49187 --app webmavenheroku
				con = DriverManager.getConnection(HOST_HEROKU + DATABASE_HEROKU, USUARIO_HEROKU, SENHA_HEROKU);
				return con;
			}
		} catch (Exception e) {
			printSQLException(null, e);
		}
		System.out.println("ConexÃ£o estabelecida com sucesso.");
		return null;
	}

	protected static void printSQLException(SQLException ex, Exception exception) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				if (((SQLException) e).getSQLState().equalsIgnoreCase("42P01")) {
					System.err.println("Tabela nÃ£o existe: ");
					break;
				} else {
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
		if (exception != null) {
			exception.printStackTrace();
		}
	}

}