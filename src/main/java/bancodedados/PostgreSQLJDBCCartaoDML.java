package bancodedados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bancodedados.dto.CartaoFidelidadeDTO;
import bancodedados.dto.ClienteDTO;

//http://brunorota.com.br/2012/05/19/tutorial-criar-crud-em-java-com-jdbc-parte-1-final/

// senha no pgadmin - 123456
// pgadmin: http://127.0.0.1:54654/browser/#

public class PostgreSQLJDBCCartaoDML extends PostgreSQLJDBCDML {
	public static void main(String args[]) {
		new PostgreSQLJDBCCartaoDML().processar();
	}

	private void processar() {
		try {
			Connection con = conectarBDPostgree();
			System.out.println("1-Inserir Dados");
			System.out.println("2-Selecionar dados cliente");
			System.out.println("3-selecionar cliente por CPF");
			System.out.println("4-Atualizar dados cliente");
			System.out.println("5-Remover dados cliente");
			System.out.println("Digite sua opção:");
			int op = this.leia.nextInt();
			switch (op) {
			case 1:
				if (con != null) {
					inserirCartao(con,new CartaoFidelidadeDTO());
				}
				break;
			case 2:
				if (con != null) {
					selecionarTodosCartoes(con);
				}
				break;
			case 3:
				if (con != null) {
					selecionarCartaoByCPF(con,new CartaoFidelidadeDTO());
				}
				break;
			case 4:
				if (con != null) {
					atualizarCPFCliente(con, new CartaoFidelidadeDTO());
				}
				break;
			case 5:
				if (con != null) {
					removerCartao(con, new CartaoFidelidadeDTO());
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
		sql.append(ID_CARTAO);
		sql.append(VIRGULA);
		sql.append(CPF_CLIENTE);
		sql.append(VIRGULA);
		sql.append(DATA_INCLUSAO);
		sql.append(FROM);
		sql.append(TABELA_CARTAO_FIDELIDADE);
		return sql;
	}

	private CartaoFidelidadeDTO selecionarCartaoByCPF(Connection connection, CartaoFidelidadeDTO cartao) {

		StringBuilder sql = montarSelect();
		sql.append(WHERE);
		sql.append(CPF_CLIENTE);
		sql.append(IGUAL_E_INTERROGACAO);
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();) {
			preparedStatement.setLong(1, cartao.getCpfCliente());
			while (rs.next()) {
				System.out.println(rs.getInt(CPF_CLIENTE));
				System.out.println(rs.getString(DATA_INCLUSAO));
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		return cartao;
	}

	public List<CartaoFidelidadeDTO> selecionarTodosCartoes(Connection connection) {
		StringBuilder sql = montarSelect();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();) {
			while (rs.next()) {
				System.out.println(rs.getInt(CPF_CLIENTE));
				System.out.println(rs.getString(NOME));
				System.out.println(rs.getString(DATA_REGISTRO));
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		return null;
	}

	public void inserirCartao(Connection con, CartaoFidelidadeDTO cartao) {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(TABELA_CARTAO_FIDELIDADE);
		
		
		sql.append(ABRE_PARENTESES);
		sql.append(CPF_CLIENTE);
		sql.append(VIRGULA);
		sql.append(NOME);
		sql.append(VIRGULA);
		sql.append(DATA_REGISTRO);
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

		try (PreparedStatement pstm = con.prepareStatement(sql.toString());) {
			pstm.setLong(1, cartao.getCpfCliente());
			pstm.setDate(3, new Date(cartao.getDataInclusao().getTime()));
			pstm.execute();

		} catch (SQLException e) {
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removerCartao(Connection con, CartaoFidelidadeDTO cartao) {
		CartaoFidelidadeDTO cartaoID = selecionarCartaoByCPF(con, cartao);
		StringBuilder sql = new StringBuilder();
		sql.append(DELETE_FROM);
		sql.append(TABELA_CARTAO_FIDELIDADE);
		sql.append(WHERE);
		sql.append(CPF_CLIENTE);
		sql.append(IGUAL);
		sql.append(INTERROGACAO);

		System.out.println("Comando delete: " + sql);

		try (PreparedStatement pstm = con.prepareStatement(sql.toString());) {
			pstm.setInt(1, cartaoID.getIdCartao());
			pstm.execute();

		} catch (SQLException e) {
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void atualizarCPFCliente(Connection con, CartaoFidelidadeDTO cartao) {
		CartaoFidelidadeDTO cartaoID = selecionarCartaoByCPF(con, cartao);
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE ");
		sql.append(TABELA_CARTAO_FIDELIDADE);
		sql.append(" SET ");
		sql.append(CPF_CLIENTE);
		sql.append(IGUAL);
		sql.append(INTERROGACAO);

		System.out.println("Comando atualizar CPF: " + sql);

		try (PreparedStatement pstm = con.prepareStatement(sql.toString());) {
			pstm.setInt(1, cartaoID.getIdCartao());
			pstm.execute();

		} catch (SQLException e) {
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
