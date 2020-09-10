package bancodedados.faculdade.dto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import bancodedados.PostgreSQLJDBCDCL;

public class CriarTabelas extends PostgreSQLJDBCDCL implements Constantes {

	public static void main(String[] args) {
		new CriarTabelas().processar();
	}

	public void criarTabelasFaculdadeViaWEB() {
		criarTabelaLOGFAQ();

	}

	public void removerTabelasFaculdadeViaWEB() {
		removerTabelaLOG();

	}

	private void processar() {

		System.out.println("1 - Criar tabelas");
		System.out.println("2 - Remover tabelas");
		int op = this.leia.nextInt();
		switch (op) {
		case 1:
//			criarTabelaFAQ();
//			criarTabelaFAQDetalhes();
			criarTabelaLOGFAQ();
			break;
		case 2:
//			removerTabelaFAQDetalhe();
//			removerTabelaFAQ();
			removerTabelaLOG();
			break;

		default:
			break;
		}
		this.leia.close();

	}

	public void criarTabelaFAQ() {

		try (Connection con = conectarBDPostgree();) {
			StringBuilder sql = new StringBuilder();
			sql.append(CREATE_TABLE_IF_NOT_EXISTS);
			sql.append(FAQ_FACULDADE_PAI);
			sql.append(ABRE_PARENTESES);

			sql.append(ID_PAI);
			sql.append(INT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(ID_DETALHE);
			sql.append(INT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(DESC_ITEM);
			sql.append(TEXT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(PRIMARY_KEY);
			sql.append(ABRE_PARENTESES);
			sql.append(ID_DETALHE);
			sql.append(FECHA_PARENTESES);
			sql.append(FECHA_PARENTESES);

			System.out.println("SQL Criar tabela " + FAQ_FACULDADE_PAI + " - " + sql);

			try (Statement stmt = con.createStatement();) {
				int ret = stmt.executeUpdate(sql.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void criarTabelaLOGFAQ() {

		try (Connection con = conectarBDPostgree();) {
			StringBuilder sql = new StringBuilder();
			sql.append(CREATE_TABLE_IF_NOT_EXISTS);
			sql.append(LOG_FACULDADE);
			sql.append(ABRE_PARENTESES);

			sql.append(ID_LOG);
			sql.append(AUTO_INCREMENT);
			sql.append(NOT_NULL);
			sql.append(VIRGULA);

			sql.append(ID_PAI);
			sql.append(INT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(ID_DETALHE);
			sql.append(INT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(DESC_ITEM);
			sql.append(TEXT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(DATA_INCLUSAO);
			sql.append(DATE_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(NOME);// nome
			sql.append(TEXT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(ID_USUARIO_TELEGRAM);// id telegram
			sql.append(BIGINT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(PRIMARY_KEY);
			sql.append(ABRE_PARENTESES);
			sql.append(ID_LOG);
			sql.append(FECHA_PARENTESES);
			sql.append(FECHA_PARENTESES);

			System.out.println("SQL Criar tabela " + LOG_FACULDADE + " - " + sql);

			try (Statement stmt = con.createStatement();) {
				int ret = stmt.executeUpdate(sql.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void removerTabelaFAQ() {

		try (Connection con = conectarBDPostgree();) {
			StringBuilder sql = new StringBuilder();
			sql.append(" DROP TABLE ");
			sql.append(FAQ_FACULDADE_PAI);

			System.out.println("SQL remover tabela " + FAQ_FACULDADE_PAI + " - " + sql);

			try (Statement stmt = con.createStatement();) {
				int ret = stmt.executeUpdate(sql.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void removerTabelaLOG() {

		try (Connection con = conectarBDPostgree();) {
			StringBuilder sql = new StringBuilder();
			sql.append(" DROP TABLE ");
			sql.append(LOG_FACULDADE);

			System.out.println("SQL remover tabela " + LOG_FACULDADE + " - " + sql);

			try (Statement stmt = con.createStatement();) {
				int ret = stmt.executeUpdate(sql.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void removerTabelaFAQDetalhe() {

		try (Connection con = conectarBDPostgree();) {
			StringBuilder sql = new StringBuilder();
			sql.append(" DROP TABLE ");
			sql.append(FAQ_FACULDADE_DETALHE);

			System.out.println("SQL remover tabela " + FAQ_FACULDADE_DETALHE + " - " + sql);

			try (Statement stmt = con.createStatement();) {
				int ret = stmt.executeUpdate(sql.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void criarTabelaFAQDetalhes() {

		try (Connection con = conectarBDPostgree();) {
			StringBuilder sql = new StringBuilder();
			sql.append(CREATE_TABLE_IF_NOT_EXISTS);
			sql.append(FAQ_FACULDADE_DETALHE);
			sql.append(ABRE_PARENTESES);

			sql.append(ID_PAI);
			sql.append(INT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(ID_DETALHE);
			sql.append(INT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(DESC_ITEM);
			sql.append(TEXT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(DESC_CONTEUDO_ALUNO);
			sql.append(TEXT_NOT_NULL);
			sql.append(VIRGULA);

			sql.append(PRIMARY_KEY);
			sql.append(ABRE_PARENTESES);
			// chave primária composta
			sql.append(ID_DETALHE);
			sql.append(VIRGULA);
			sql.append(ID_PAI);
			sql.append(FECHA_PARENTESES);
			sql.append(VIRGULA);
			// chave estrangeira
			sql.append(FOREIGN_KEY);
			sql.append(ABRE_PARENTESES);
			sql.append(ID_DETALHE);
			sql.append(FECHA_PARENTESES);

			sql.append(REFERENCES);
			sql.append(FAQ_FACULDADE_PAI);
			sql.append(ABRE_PARENTESES);
			sql.append(ID_DETALHE);
			sql.append(FECHA_PARENTESES);
			sql.append(FECHA_PARENTESES);

			System.out.println("SQL Criar tabela " + FAQ_FACULDADE_DETALHE + " - " + sql);

			try (Statement stmt = con.createStatement();) {
				int ret = stmt.executeUpdate(sql.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
