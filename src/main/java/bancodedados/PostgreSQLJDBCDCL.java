package bancodedados;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//http://brunorota.com.br/2012/05/19/tutorial-criar-crud-em-java-com-jdbc-parte-1-final/

// senha no pgadmin - 123456
// pgadmin: http://127.0.0.1:54654/browser/#
//http://localhost:8080/webmavenheroku/postgree.jsf

public class PostgreSQLJDBCDCL extends PostgreSQLJDBC {

	public static final String AUTO_INCREMENT = " bigserial ";
	public static final String REFERENCES = " REFERENCES ";
	public static final String FOREIGN_KEY = " FOREIGN KEY ";

	public static final String CREATE_TABLE_IF_NOT_EXISTS = " CREATE TABLE IF NOT EXISTS  ";
	public static final String DROP_TABLE = " DROP TABLE ";
	public static final String PRIMARY_KEY = " PRIMARY KEY ";

	public static final String TEXT_NULL = " TEXT NULL ";
	public static final String TEXT_NOT_NULL = " TEXT NOT NULL ";
	public static final String DOUBLE_NOT_NULL = " DOUBLE PRECISION NOT NULL ";
	public static final String INT_NOT_NULL = " INT NOT NULL ";
	public static final String BIGINT_NOT_NULL = " BIGINT NOT NULL ";
	public static final String NOT_NULL = " NOT NULL ";
	public static final String DATE_NOT_NULL = " DATE NOT NULL";

	public static void main(String args[]) {
		new PostgreSQLJDBCDCL().processar();
	}

	public void criarTabelasViaWEB() {
		try {
			Connection con = conectarBDPostgree();
			if (con != null) {
				criarTabelaClienteDTO(con);
				criarTabelaEnderecoDTO(con);
				criarTabelaPedidoDTO(con);
				criarTabelaItemPedidoDTO(con);
				criarTabelaPagamentoDTO(con);
				criarTabelaBonus(con);
				criarTabelaCartao(con);
				con.close();
			} else {
				System.out.println("Não foi possível obter uma conexão com o banco de dados para criar as tabelas.");
			}
		} catch (SQLException e) {
			System.out.println("Problemas na criação de tabelas.");
			e.printStackTrace();
		}
	}

	public void removerTabelasViaWEB() {
		try {
			Connection con = conectarBDPostgree();
			if (con != null) {
				removerTabelaCartao(con);
				removerTabelaBonus(con);
				removerTabelaEnderecoDTO(con);
				removerTabelaItemPedidoDTO(con);
				removerTabelaPagamentoDTO(con);
				removerTabelaPedidoDTO(con);
				removerTabelaClienteDTO(con);
				con.close();
			} else {
				System.out.println("Não foi possível obter uma conexão com o banco de dados para remover as tabelas.");
			}
		} catch (SQLException e) {
			System.out.println("Problemas na remoção de tabelas.");
			e.printStackTrace();
		}

	}

	private void processar() {

		try {
			Connection con = conectarBDPostgree();
			System.out.println("1-criar tabelas");
			System.out.println("2-remover tabelas");

			System.out.println("Digite sua opção:");
			int op = this.leia.nextInt();

			switch (op) {
			case 1:
				if (con != null) {
					criarTabelaClienteDTO(con);
					criarTabelaEnderecoDTO(con);
					criarTabelaPedidoDTO(con);
					criarTabelaItemPedidoDTO(con);
					criarTabelaPagamentoDTO(con);
					criarTabelaBonus(con);
					criarTabelaCartao(con);

				}
				break;
			case 2:
				if (con != null) {
					// Devido a chave estrangeira a remoÃ§Ã£o da tabela deve ser inversaa criaÃ§Ã£o.
					removerTabelaCartao(con);
					removerTabelaBonus(con);
					removerTabelaEnderecoDTO(con);
					removerTabelaItemPedidoDTO(con);
					removerTabelaPagamentoDTO(con);
					removerTabelaPedidoDTO(con);
					removerTabelaClienteDTO(con);

				}
				break;

			default:
				break;
			}

			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void removerTabelaEnderecoDTO(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(DROP_TABLE);
		sql.append(TABELA_ENDERECO_DTO);
		System.out.println("Comando remover tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void removerTabelaPagamentoDTO(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(DROP_TABLE);
		sql.append(TABELA_PAGAMENTO_DTO);
		System.out.println("Comando remover tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void removerTabelaPedidoDTO(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(DROP_TABLE);
		sql.append(TABELA_PEDIDO_DTO);
		System.out.println("Comando remover tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void removerTabelaItemPedidoDTO(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(DROP_TABLE);
		sql.append(TABELA_ITEM_PEDIDO_DTO);
		System.out.println("Comando remover tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void removerTabelaClienteDTO(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(DROP_TABLE);
		sql.append(TABELA_CLIENTE_DTO);
		System.out.println("Comando remover tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void removerTabelaCartao(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(DROP_TABLE);
		sql.append(TABELA_CARTAO_FIDELIDADE);
		System.out.println("Comando remover tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void removerTabelaBonus(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(DROP_TABLE);
		sql.append(TABELA_BONUS_CARTAO_FIDELIDADE);
		System.out.println("Comando remover tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void criarTabelaBonus(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(CREATE_TABLE_IF_NOT_EXISTS);
		sql.append(TABELA_BONUS_CARTAO_FIDELIDADE);

		sql.append(ABRE_PARENTESES);
		sql.append(ID_BONUS);
		sql.append(AUTO_INCREMENT);
		sql.append(NOT_NULL);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(BONUS_CONSUMIDO);
		sql.append(INT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(BONUS_ATIVADO);
		sql.append(INT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(DATA_INCLUSAO);
		sql.append(DATE_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(DATA_VENCIMENTO);
		sql.append(DATE_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(PRIMARY_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(ID_BONUS);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(FECHA_PARENTESES);
		sql.append(VIRGULA);
		// FOREIGN KEY (col1, col2) REFERENCES tabela-pai (col1, col2);
		sql.append(FOREIGN_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(FECHA_PARENTESES);

		sql.append(REFERENCES);
		sql.append(TABELA_CLIENTE_DTO);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(FECHA_PARENTESES);
		sql.append(FECHA_PARENTESES);
		System.out.println("Comando criar tabela bonus: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}

	}

	private void criarTabelaCartao(Connection con) {
		// A FK deve "bater" com a respectiva PK. Eu reveria seu padrão de nomenclatura.
		StringBuilder sql = new StringBuilder();
		sql.append(CREATE_TABLE_IF_NOT_EXISTS);
		sql.append(TABELA_CARTAO_FIDELIDADE);
		sql.append(ABRE_PARENTESES);

		sql.append(ID_CARTAO);
		sql.append(AUTO_INCREMENT);
		sql.append(NOT_NULL);
		sql.append(VIRGULA);

		sql.append(ID_PEDIDO);
		sql.append(INT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(ID_BONUS);
		sql.append(INT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(TELEFONE);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(ESTADO_SELO_CARTAO);
		sql.append(INT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(DATA_INCLUSAO);
		sql.append(DATE_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(DATA_VENCIMENTO);
		sql.append(DATE_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(PRIMARY_KEY);
		sql.append(ABRE_PARENTESES);

		sql.append(ID_CARTAO);
		sql.append(VIRGULA);
		sql.append(ID_PEDIDO);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_BONUS);
		sql.append(FECHA_PARENTESES);
		sql.append(VIRGULA);
		sql.append(FOREIGN_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_PEDIDO);
		sql.append(FECHA_PARENTESES);

		sql.append(REFERENCES);
		sql.append(TABELA_PEDIDO_DTO);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_PEDIDO);
		sql.append(FECHA_PARENTESES);
		sql.append(VIRGULA);

		sql.append(FOREIGN_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_BONUS);
		sql.append(FECHA_PARENTESES);
		sql.append(REFERENCES);
		sql.append(TABELA_BONUS_CARTAO_FIDELIDADE);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_BONUS);
		sql.append(FECHA_PARENTESES);
		sql.append(FECHA_PARENTESES);

		System.out.println("Comando criar tabela cartao: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void criarTabelaClienteDTO(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(CREATE_TABLE_IF_NOT_EXISTS);
		sql.append(TABELA_CLIENTE_DTO);
		sql.append(ABRE_PARENTESES);
		sql.append(ID_CLIENTE);
		sql.append(AUTO_INCREMENT);
		sql.append(NOT_NULL);
		sql.append(VIRGULA);
		sql.append(CPF_CLIENTE);// cpf
		sql.append(BIGINT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(NOME);// nome
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(DATA_REGISTRO);// data
		sql.append(DATE_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(PERMITE_EMAIL_SMS);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(EMAIL);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(EMPRESA);
		sql.append(INT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(TELEFONE);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(ID_USUARIO_TELEGRAM);// id telegram
		sql.append(BIGINT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(PRIMARY_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(FECHA_PARENTESES);
		sql.append(FECHA_PARENTESES);

		System.out.println("Comando criar tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void criarTabelaEnderecoDTO(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(CREATE_TABLE_IF_NOT_EXISTS);
		sql.append(TABELA_ENDERECO_DTO);
		sql.append(ABRE_PARENTESES);

		sql.append(UF);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(CIDADE);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(BAIRRO);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(TIPO_LOGRADOURO);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(LOGRADOURO);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(NUMERO);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(CEP);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(PRIMARY_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(FECHA_PARENTESES);
		sql.append(VIRGULA);

		sql.append(FOREIGN_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);//
		sql.append(FECHA_PARENTESES);
		sql.append(REFERENCES);
		sql.append(TABELA_CLIENTE_DTO);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(FECHA_PARENTESES);
		sql.append(FECHA_PARENTESES);

		System.out.println("Comando criar tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void criarTabelaPedidoDTO(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(CREATE_TABLE_IF_NOT_EXISTS);
		sql.append(TABELA_PEDIDO_DTO);
		sql.append(ABRE_PARENTESES);
		sql.append(ID_PEDIDO);
		sql.append(AUTO_INCREMENT);
		sql.append(NOT_NULL);
		sql.append(VIRGULA);

		sql.append(DATA_REGISTRO);// data
		sql.append(DATE_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(VALOR_PEDIDO);
		sql.append(DOUBLE_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(URL_RECIBO);
		sql.append(TEXT_NULL);

		sql.append(VIRGULA);
		sql.append(ESTADO_PEDIDO);
		sql.append(INT_NOT_NULL);

		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(PRIMARY_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_PEDIDO);
		sql.append(FECHA_PARENTESES);
		sql.append(VIRGULA);
		sql.append(FOREIGN_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);//
		sql.append(FECHA_PARENTESES);
		sql.append(REFERENCES);
		sql.append(TABELA_CLIENTE_DTO);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(FECHA_PARENTESES);
		sql.append(FECHA_PARENTESES);

		System.out.println("Comando criar tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void criarTabelaItemPedidoDTO(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(CREATE_TABLE_IF_NOT_EXISTS);
		sql.append(TABELA_ITEM_PEDIDO_DTO);
		sql.append(ABRE_PARENTESES);
		sql.append(ID_ITEM_PEDIDO);
		sql.append(AUTO_INCREMENT);
		sql.append(NOT_NULL);
		sql.append(VIRGULA);

		sql.append(ID_PEDIDO);
		sql.append(INT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(DESCRICAO_ESTILO);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(DATA_REGISTRO);// data
		sql.append(DATE_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(VALOR_ITEM_PEDIDO);
		sql.append(DOUBLE_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(TELEFONE);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(PRIMARY_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_ITEM_PEDIDO);
		sql.append(VIRGULA);
		sql.append(ID_PEDIDO);
		sql.append(FECHA_PARENTESES);
		sql.append(VIRGULA);
		sql.append(FOREIGN_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);//
		sql.append(VIRGULA);
		sql.append(ID_PEDIDO);
		sql.append(FECHA_PARENTESES);
		sql.append(REFERENCES);
		sql.append(TABELA_PEDIDO_DTO);
		sql.append(ABRE_PARENTESES);

		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_PEDIDO);

		sql.append(FECHA_PARENTESES);
		sql.append(FECHA_PARENTESES);

		System.out.println("Comando criar tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private void criarTabelaPagamentoDTO(Connection con) {
		StringBuilder sql = new StringBuilder();
		sql.append(CREATE_TABLE_IF_NOT_EXISTS);
		sql.append(TABELA_PAGAMENTO_DTO);
		sql.append(ABRE_PARENTESES);

		sql.append(ID_PAGAMENTO);
		sql.append(AUTO_INCREMENT);
		sql.append(NOT_NULL);
		sql.append(VIRGULA);

		sql.append(ID_PEDIDO);
		sql.append(INT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(DATA_REGISTRO);// data
		sql.append(DATE_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(TELEFONE);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(ID_FORMA_PAGAMENTO);// data
		sql.append(INT_NOT_NULL);
		sql.append(VIRGULA);

		sql.append(PRIMARY_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_PEDIDO);
		sql.append(FECHA_PARENTESES);

		sql.append(VIRGULA);

		sql.append(FOREIGN_KEY);
		sql.append(ABRE_PARENTESES);
		sql.append(TELEFONE);//
		sql.append(VIRGULA);
		sql.append(ID_PEDIDO);
		sql.append(FECHA_PARENTESES);
		sql.append(REFERENCES);
		sql.append(TABELA_PEDIDO_DTO);
		sql.append(ABRE_PARENTESES);

		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_PEDIDO);
		sql.append(FECHA_PARENTESES);
		sql.append(FECHA_PARENTESES);

		System.out.println("Comando criar tabela: " + sql);
		try (Statement statement = con.createStatement();) {
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

}