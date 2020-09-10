package bancodedados.faculdade.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import bancodedados.PostgreSQLJDBCDCL;

public class InsereDados extends PostgreSQLJDBCDCL implements Constantes {

	public static void main(String[] args) {
		new InsereDados().processar();
	}

	private void processar() {
//		FAQ f = new FAQ(0, 1, "Teste 1");
//		inserirFAQ(f);
//		f = new FAQ(0, 2, "Teste 2");
//		inserirFAQ(f);
//		f = new FAQ(0, 3, "Teste 3");
//		inserirFAQ(f);
//
//		FAQ f2 = new FAQ(1, 1, "Teste", "Conteudo Teste");
//		inserirFAQDetalhe(f2);
//		f2 = new FAQ(1, 2, "Teste", "Conteudo Teste");
//		inserirFAQDetalhe(f2);
//		f2 = new FAQ(1, 4, "Teste", "Conteudo Teste");
//		inserirFAQDetalhe(f2);

		LOGFaculdade log = new LOGFaculdade(1l, "Douglas", 0, 2, "Academico",new Date());
		inserirLOG(log);
	}
	// Inserir dados
	// 1 - FAQ

	public void inserirLOG(LOGFaculdade logDTO) {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(LOG_FACULDADE);
		sql.append(ABRE_PARENTESES);

		sql.append(ID_PAI);
		sql.append(VIRGULA);

		sql.append(ID_DETALHE);
		sql.append(VIRGULA);

		sql.append(DESC_ITEM);
		sql.append(VIRGULA);

		sql.append(DATA_INCLUSAO);
		sql.append(VIRGULA);

		sql.append(NOME);// nome
		sql.append(VIRGULA);

		sql.append(ID_USUARIO_TELEGRAM);// id telegram
		sql.append(FECHA_PARENTESES);

		sql.append(VALUES);
		sql.append(ABRE_PARENTESES);
		sql.append(INTERROGACAO);
		sql.append(VIRGULA);
		sql.append(INTERROGACAO);
		sql.append(VIRGULA);
		sql.append(INTERROGACAO);
		sql.append(VIRGULA);
		sql.append(INTERROGACAO);
		sql.append(VIRGULA);
		sql.append(INTERROGACAO);
		sql.append(VIRGULA);
		sql.append(INTERROGACAO);
		sql.append(FECHA_PARENTESES);
		System.out.println("Comando insert: " + sql);

		try (Connection con = conectarBDPostgree(); PreparedStatement pstmCli = con.prepareStatement(sql.toString());) {

			pstmCli.setInt(1, logDTO.getIdPai());
			pstmCli.setInt(2, logDTO.getIdItem());
			pstmCli.setString(3, logDTO.getDescItem());
			pstmCli.setDate(4, new java.sql.Date(logDTO.getDataInclusao().getTime()));
			pstmCli.setString(5, logDTO.getNomeUsuario());
			pstmCli.setLong(6, logDTO.getIdUsuarioTelegram());
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

	public void inserirFAQ(FAQ faqdto) {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(FAQ_FACULDADE_PAI);
		sql.append(ABRE_PARENTESES);

		sql.append(ID_PAI);
		sql.append(VIRGULA);
		sql.append(ID_DETALHE);
		sql.append(VIRGULA);
		sql.append(DESC_ITEM);
		sql.append(FECHA_PARENTESES);
		sql.append(VALUES);
		sql.append(ABRE_PARENTESES);
		sql.append(INTERROGACAO);
		sql.append(VIRGULA);
		sql.append(INTERROGACAO);
		sql.append(VIRGULA);
		sql.append(INTERROGACAO);
		sql.append(FECHA_PARENTESES);
		System.out.println("Comando insert: " + sql);

		try (Connection con = conectarBDPostgree(); PreparedStatement pstmCli = con.prepareStatement(sql.toString());) {

			pstmCli.setInt(1, faqdto.getIdPai());
			pstmCli.setInt(2, faqdto.getIdItem());
			pstmCli.setString(3, faqdto.getDescItem());
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
	public void inserirFAQDetalhe(FAQ faqdto) {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(FAQ_FACULDADE_DETALHE);
		sql.append(ABRE_PARENTESES);

		sql.append(ID_PAI);
		sql.append(VIRGULA);
		sql.append(ID_DETALHE);
		sql.append(VIRGULA);
		sql.append(DESC_ITEM);
		sql.append(VIRGULA);
		sql.append(DESC_CONTEUDO_ALUNO);
		sql.append(FECHA_PARENTESES);
		sql.append(VALUES);
		sql.append(ABRE_PARENTESES);
		sql.append(INTERROGACAO);
		sql.append(VIRGULA);
		sql.append(INTERROGACAO);
		sql.append(VIRGULA);
		sql.append(INTERROGACAO);
		sql.append(VIRGULA);
		sql.append(INTERROGACAO);
		sql.append(FECHA_PARENTESES);
		System.out.println("Comando insert: " + sql);

		try (Connection con = conectarBDPostgree(); PreparedStatement pstmCli = con.prepareStatement(sql.toString());) {

			pstmCli.setInt(1, faqdto.getIdPai());
			pstmCli.setInt(2, faqdto.getIdItem());
			pstmCli.setString(3, faqdto.getDescItem());
			pstmCli.setString(4, faqdto.getConteudoAluno());
			pstmCli.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
