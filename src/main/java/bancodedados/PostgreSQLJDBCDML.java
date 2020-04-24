package bancodedados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bancodedados.dto.ClienteDTO;

//http://brunorota.com.br/2012/05/19/tutorial-criar-crud-em-java-com-jdbc-parte-1-final/

// senha no pgadmin - 123456
// pgadmin: http://127.0.0.1:54654/browser/#

public class PostgreSQLJDBCDML extends PostgreSQLJDBC {
	protected static final String VALUES = " VALUES ";
	protected static final String DELETE_FROM = " DELETE FROM ";
	protected static final String IGUAL = " = ";
	protected static final String INTERROGACAO = "?";
	protected static final String INSERT_INTO = " INSERT INTO ";
	protected static final String IGUAL_E_INTERROGACAO = " =? ";
	protected static final String WHERE = " WHERE ";
	protected static final String FROM = " FROM ";
	protected static final String SELECT = " SELECT ";
 

}
