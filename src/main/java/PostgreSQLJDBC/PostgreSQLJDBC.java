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

public class PostgreSQLJDBC {
	private static final String ORG_POSTGRESQL_DRIVER = "org.postgresql.Driver";
	private static final String LOCAL_POSTGREE = "jdbc:postgresql://localhost:5432/";
	private static final String USUARIO_PORTGREE = "postgres";
	private static final String SENHA_BD = "123456";
	private static final String DATABASE = "faculdade";
 
	protected Scanner leia = new Scanner(System.in);

	protected Connection conectarBDPostgree() {
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

	protected static void printSQLException(SQLException ex, Exception exception) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				if (((SQLException) e).getSQLState().equalsIgnoreCase("42P01")) {
					System.err.println("Tabela não existe: ");
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