package bancodedados.faculdade.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodedados.PostgreSQLJDBCDCL;

public class PesquisaFaculdade extends PostgreSQLJDBCDCL implements Constantes {
	public static void main(String[] args) {
		
		new PesquisaFaculdade().processar();;
	}
	private void processar() {
 
		selecionarLogs( );
		 
	}
	public List<LOGFaculdade> selecionarLogs( ) {
		
		StringBuilder sql = montarSelect();
		List<LOGFaculdade> lista = new ArrayList<>(); 
		try (Connection connection = conectarBDPostgree();PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();) {
			while (rs.next()) {
				LOGFaculdade log = new LOGFaculdade();
				log.setIdPai(rs.getInt(ID_PAI));
				log.setIdItem(rs.getInt(ID_DETALHE));
				log.setDescItem(rs.getString(DESC_ITEM));
				log.setDataInclusao(rs.getDate(DATA_INCLUSAO));
				log.setNomeUsuario(rs.getString(NOME));
				log.setIdUsuarioTelegram(rs.getLong(ID_USUARIO_TELEGRAM));
				System.out.println(log);
				lista.add(log);
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		return null;
	}

	private StringBuilder montarSelect() {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT);
		sql.append(ID_PAI);
		sql.append(VIRGULA);
		sql.append(ID_DETALHE);
		sql.append(VIRGULA);
		sql.append(DESC_ITEM);
		sql.append(VIRGULA);
		sql.append(DATA_INCLUSAO);
		sql.append(VIRGULA);
		sql.append(NOME);
		sql.append(VIRGULA);
		sql.append(ID_USUARIO_TELEGRAM);
		sql.append(FROM);
		sql.append(LOG_FACULDADE);
		return sql;
	}
}
