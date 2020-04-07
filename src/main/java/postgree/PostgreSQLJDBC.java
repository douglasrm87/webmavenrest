package postgree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//http://brunorota.com.br/2012/05/19/tutorial-criar-crud-em-java-com-jdbc-parte-1-final/

// senha no pgadmin - 123456
// pgadmin: http://127.0.0.1:54654/browser/#

public class PostgreSQLJDBC {

	private static final String STRING = " =? ";
	private static final String WHERE = " WHERE ";
	private static final String FROM = " FROM ";
	private static final String SELECT = " SELECT ";
	private static final String ORG_POSTGRESQL_DRIVER = "org.postgresql.Driver";
	private static final String LOCAL_POSTGREE = "jdbc:postgresql://localhost:5432/";
	private static final String CREATE_TABLE_IF_NOT_EXISTS = " CREATE TABLE IF NOT EXISTS  ";
	private static final String VIRGULA = " , ";
	private static final String PRIMARY_KEY = " PRIMARY KEY ";
	private static final String FECHA_PARENTESES = " ) ";
	private static final String ABRE_PARENTESES = " ( ";
	private static final String TEXT_NOT_NULL = " TEXT NOT NULL ";
	private static final String INT_NOT_NULL = " INT NOT NULL ";
	private static final String DATE_NOT_NULL = " DATE NOT NULL";
	private static final String USUARIO_PORTGREE = "postgres";
	private static final String SENHA_BD = "123456";
	private static final String DATABASE = "faculdade";
	private static final String TABELA_CARTAO_FIDELIDADE = "CARTAO_FIDELIDADE";

	private static final String CPF_CLIENTE = " cpf_cliente ";
	private static final String NOME = " nome ";
	private static final String QUANTIDADE_SELO = " quantidade_selo ";
	private static final String DATA_REGISTRO = " data_registro ";

	public static void main(String args[]) {

		try {
			System.out.println("1-conectar DB");
			PostgreSQLJDBC obj = new PostgreSQLJDBC();
			Connection con = obj.conectarBDPostgree();
//			obj.criarTabelaBDPostgree(con);
			obj.selecionarCliente(con);			
			con.close();
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

	private Connection conectarBDPostgree() {
		Connection con = null;
		try {
			Class.forName(ORG_POSTGRESQL_DRIVER);
			con = DriverManager.getConnection(LOCAL_POSTGREE + DATABASE, USUARIO_PORTGREE, SENHA_BD);
		} catch (Exception e) {
			printSQLException(null, e);
		}
		System.out.println("Opened database successfully");
		return con;
	}

	private void criarTabelaBDPostgree(Connection con) {

		StringBuilder sql = new StringBuilder();
		sql.append(CREATE_TABLE_IF_NOT_EXISTS);
		sql.append(TABELA_CARTAO_FIDELIDADE);
		sql.append(ABRE_PARENTESES);
		sql.append(CPF_CLIENTE);
		sql.append(INT_NOT_NULL);
		sql.append(PRIMARY_KEY);
		sql.append(VIRGULA);
		sql.append(NOME);
		sql.append(TEXT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(QUANTIDADE_SELO);
		sql.append(INT_NOT_NULL);
		sql.append(VIRGULA);
		sql.append(DATA_REGISTRO);
		sql.append(DATE_NOT_NULL);
		sql.append(FECHA_PARENTESES);

		System.out.println("Comando criar tabela: " + sql);
		// Step 2:Create a statement using connection object
		try (Statement statement = con.createStatement();) {

			// Step 3: Execute the query or update query
			statement.execute(sql.toString());
		} catch (SQLException e) {
			printSQLException(e, null);
		}

	}

	private static void printSQLException(SQLException ex, Exception exception) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
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
		if (exception != null) {
			exception.printStackTrace();
		}
	}

	private StringBuilder montarSelect() {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT);
		sql.append(CPF_CLIENTE);
		sql.append(VIRGULA);
		sql.append(NOME);
		sql.append(VIRGULA);
		sql.append(QUANTIDADE_SELO);
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
				System.out.println(rs.getString(QUANTIDADE_SELO));
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
				System.out.println(rs.getString(QUANTIDADE_SELO));
				System.out.println(rs.getString(DATA_REGISTRO));
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
	}

}