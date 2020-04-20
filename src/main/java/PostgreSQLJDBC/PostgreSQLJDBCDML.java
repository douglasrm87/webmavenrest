package PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

//http://brunorota.com.br/2012/05/19/tutorial-criar-crud-em-java-com-jdbc-parte-1-final/

// senha no pgadmin - 123456
// pgadmin: http://127.0.0.1:54654/browser/#

public class PostgreSQLJDBCDML extends PostgreSQLJDBC {
	private static final String STRING = " =? ";
	private static final String WHERE = " WHERE ";
	private static final String FROM = " FROM ";
	private static final String SELECT = " SELECT ";

	private static final String VIRGULA = " , ";

	private static final String TABELA_CARTAO_FIDELIDADE = "CARTAO_FIDELIDADE";

	private static final String CPF_CLIENTE = " cpf_cliente ";
	private static final String NOME = " nome ";
	private static final String DATA_REGISTRO = " data_registro ";

	public static void main(String args[]) {
		new PostgreSQLJDBCDML().processar();
	}

	private void processar() {
		try {
			Connection con = conectarBDPostgree();
			System.out.println("3-selecionar dados cliente");
			System.out.println("Digite sua opção:");
			int op = this.leia.nextInt();
			switch (op) {
			case 3:
				if (con != null) {
					selecionarCliente(con);
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

	private StringBuilder montarSelect() {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT);
		sql.append(CPF_CLIENTE);
		sql.append(VIRGULA);
		sql.append(NOME);
		sql.append(VIRGULA);
		sql.append(DATA_REGISTRO);
		sql.append(FROM);
		sql.append(TABELA_CARTAO_FIDELIDADE);
		return sql;
	}

	private void selecionarClienteByCPF(Connection connection, int cpf) {
		StringBuilder sql = montarSelect();
		sql.append(WHERE);
		sql.append(CPF_CLIENTE);
		sql.append(STRING);

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();) {

			preparedStatement.setInt(1, cpf);
			System.out.println(preparedStatement);

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				System.out.println(rs.getInt(CPF_CLIENTE));
				System.out.println(rs.getString(NOME));
				System.out.println(rs.getString(DATA_REGISTRO));
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	public void selecionarCliente(Connection connection) {
		StringBuilder sql = montarSelect();

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();) {

			System.out.println(preparedStatement);

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				System.out.println(rs.getInt(CPF_CLIENTE));
				System.out.println(rs.getString(NOME));
				System.out.println(rs.getString(DATA_REGISTRO));
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

}

//private static final String AUTO_INCREMENT = " serial ";
//private static final String REFERENCES = " REFERENCES ";
//private static final String FOREIGN_KEY = " FOREIGN KEY ";
//private static final String ORG_POSTGRESQL_DRIVER = "org.postgresql.Driver";
//private static final String LOCAL_POSTGREE = "jdbc:postgresql://localhost:5432/";
//private static final String CREATE_TABLE_IF_NOT_EXISTS = " CREATE TABLE IF NOT EXISTS  ";
//private static final String DROP_TABLE = " DROP TABLE ";
//private static final String PRIMARY_KEY = " PRIMARY KEY ";
//private static final String TEXT_NOT_NULL = " TEXT NOT NULL ";
//private static final String INT_NOT_NULL = " INT NOT NULL ";
//private static final String NOT_NULL = " NOT NULL ";
//private static final String DATE_NOT_NULL = " DATE NOT NULL";
//private static final String USUARIO_PORTGREE = "postgres";
//private static final String SENHA_BD = "123456";
//private static final String DATABASE = "faculdade";