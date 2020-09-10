package bancodedados.faculdade.dto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bancodedados.PostgreSQLJDBCDCL;

public class RemoveDados extends PostgreSQLJDBCDCL implements Constantes{

	public static void main(String[] args) {
		new RemoveDados().processar();
	}

	private void processar() {
		FAQ f2 = new FAQ(0, 1, "Teste","Conteudo Teste");
		removerFAQDetalhe(f2);
		FAQ f = new FAQ(0, 1, "Teste");
		removerFAQ(f);
		

	}
	// Inserir dados
	// 1 - FAQ

	public void removerFAQ(FAQ faqdto) {
		StringBuilder sql = new StringBuilder();
		sql.append(DELETE_FROM);
		sql.append(FAQ_FACULDADE_PAI);
		System.out.println("Comando delete: " + sql);

		 
		try (Connection con = conectarBDPostgree();
				PreparedStatement pstmCli = con.prepareStatement(sql.toString());) {
			pstmCli.execute();

		} catch (SQLException e) {
			if (REGISTRO_DUPLICADO.equals(e.getSQLState())) {
				System.out.println("Item duplicado");
			} else {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 2 - FAQ Detalhe
	public void removerFAQDetalhe(FAQ faqdto) {
		StringBuilder sql = new StringBuilder();
		sql.append(DELETE_FROM);
		sql.append(FAQ_FACULDADE_DETALHE);
		 
		System.out.println("Comando delete: " + sql);

	 
		try (Connection con = conectarBDPostgree();
				PreparedStatement pstmCli = con.prepareStatement(sql.toString());) {
			pstmCli.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
