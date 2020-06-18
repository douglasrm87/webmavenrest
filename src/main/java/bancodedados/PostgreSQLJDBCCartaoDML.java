package bancodedados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodedados.dto.BonusCartaoFidelidadeDTO;
import bancodedados.dto.CartaoFidelidadeDTO;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.ItemPedidoDTO;
import bancodedados.dto.PedidoDTO;

//http://brunorota.com.br/2012/05/19/tutorial-criar-crud-em-java-com-jdbc-parte-1-final/

// senha no pgadmin - 123456
// pgadmin: http://127.0.0.1:54654/browser/#

public class PostgreSQLJDBCCartaoDML extends PostgreSQLJDBCDML {
	private static final String SET2 = " SET ";
	private static final String UPDATE2 = " UPDATE ";
	private static int BLOQUEADO_MENOR_10 = 0;
	private static int LIBERADOADO_MAIOR_IGUAL_10 = 1;
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
//					inserirCartao(con, new CartaoFidelidadeDTO());
				}
				break;
			case 2:
				if (con != null) {
					selecionarTodosCartoes(con);
				}
				break;
			case 3:
				if (con != null) {
					selecionarCartaoByCPF(con, new CartaoFidelidadeDTO());
				}
				break;
			case 4:
				 
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
//			preparedStatement.setLong(1, cartao.getCpfCliente());
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

	public void cadastrarItemCartao(BonusCartaoFidelidadeDTO bonus, PedidoDTO pedido) {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(TABELA_CARTAO_FIDELIDADE);
		sql.append(ABRE_PARENTESES);

		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_PEDIDO);

		sql.append(VIRGULA);
		sql.append(ID_BONUS);
		sql.append(VIRGULA);
		sql.append(ESTADO_SELO_CARTAO);
		sql.append(VIRGULA);
		sql.append(DATA_INCLUSAO);
		sql.append(VIRGULA);
		sql.append(DATA_VENCIMENTO);

		sql.append(FECHA_PARENTESES);

		sql.append(VALUES);
		sql.append(ABRE_PARENTESES);
		int tam = 6;
		for (int i = 0; i < tam; i++) {
			sql.append(INTERROGACAO);
			if (i < (tam - 1)) {
				sql.append(VIRGULA);
			}
		}
		sql.append(FECHA_PARENTESES);
		System.out.println("Comando insert cartao: " + sql);
		try (Connection con = conectarBDPostgree(); PreparedStatement pstm = con.prepareStatement(sql.toString());) {
			pstm.setString(1, pedido.getTelefone());
			pstm.setInt(2, pedido.getIdPedido());
			pstm.setInt(3, bonus.getIdBonus());
			pstm.setInt(4, BLOQUEADO_MENOR_10);
			pstm.setDate(5, new Date(new java.util.Date().getTime()));
			pstm.setDate(6, new Date(new java.util.Date().getTime()));
			pstm.execute();

		} catch (SQLException e) {
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cadastrarBonus(ClienteDTO cliente) {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(TABELA_BONUS_CARTAO_FIDELIDADE);
		sql.append(ABRE_PARENTESES);

		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(BONUS_CONSUMIDO);
		sql.append(VIRGULA);
		sql.append(BONUS_ATIVADO);
		sql.append(VIRGULA);
		sql.append(DATA_INCLUSAO);
		sql.append(VIRGULA);
		sql.append(DATA_VENCIMENTO);

		sql.append(FECHA_PARENTESES);
		sql.append(VALUES);
		sql.append(ABRE_PARENTESES);
		int tam = 5;
		for (int i = 0; i < tam; i++) {
			sql.append(INTERROGACAO);
			if (i < (tam - 1)) {
				sql.append(VIRGULA);
			}
		}
		sql.append(FECHA_PARENTESES);
		System.out.println("Comando insert bonus: " + sql);

		try (Connection con = conectarBDPostgree(); PreparedStatement pstm = con.prepareStatement(sql.toString());) {
			pstm.setString(1, cliente.getTelefone());
			pstm.setInt(2, 0);
			pstm.setInt(3, 0);
			pstm.setDate(4, new Date(new java.util.Date().getTime()));
			pstm.setDate(5, new Date(new java.util.Date().getTime()));
			pstm.execute();

		} catch (SQLException e) {
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removerCartao(Connection con, CartaoFidelidadeDTO cartao) {
//		CartaoFidelidadeDTO cartaoID = selecionarCartaoByCPF(con, cartao);
//		StringBuilder sql = new StringBuilder();
//		sql.append(DELETE_FROM);
//		sql.append(TABELA_CARTAO_FIDELIDADE);
//		sql.append(WHERE);
//		sql.append(CPF_CLIENTE);
//		sql.append(IGUAL);
//		sql.append(INTERROGACAO);
//
//		System.out.println("Comando delete: " + sql);
//
//		try (PreparedStatement pstm = con.prepareStatement(sql.toString());) {
//			pstm.setInt(1, cartaoID.getIdCartao());
//			pstm.execute();
//
//		} catch (SQLException e) {
//			printSQLException(e, null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}


	private StringBuilder montarSelectBonus() {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT);
		sql.append(ID_BONUS);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(BONUS_CONSUMIDO);
		sql.append(VIRGULA);
		sql.append(BONUS_ATIVADO);
		sql.append(VIRGULA);
		sql.append(DATA_INCLUSAO);
		sql.append(VIRGULA);
		sql.append(DATA_VENCIMENTO);
		sql.append(FROM);
		sql.append(TABELA_BONUS_CARTAO_FIDELIDADE);
		return sql;
	}

	
	public void desativarBonusPromoverPremiacao(BonusCartaoFidelidadeDTO bonus) {
		StringBuilder sql = new StringBuilder();
		sql.append(UPDATE2);
		sql.append(TABELA_BONUS_CARTAO_FIDELIDADE);
		sql.append(SET2);
		sql.append(BONUS_ATIVADO);
		sql.append(IGUAL);
		sql.append(INTERROGACAO);
		
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(AND);
		sql.append(BONUS_ATIVADO); // fica ativo ate atingir 10 selos 
		sql.append(IGUAL_E_INTERROGACAO);
		
		System.out.println("Comando atualizar CPF: " + sql);
		
		try (Connection con = conectarBDPostgree();  PreparedStatement pstm = con.prepareStatement(sql.toString());) {
			pstm.setInt(1, LIBERADOADO_MAIOR_IGUAL_10);
			pstm.setString(2, bonus.getTelefone());
			pstm.setInt(3, BLOQUEADO_MENOR_10);
			pstm.execute();

		} catch (SQLException e) {
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public BonusCartaoFidelidadeDTO selecionarBonusNoConsumidoNoAtivado(ClienteDTO cliente) {
		StringBuilder sql = montarSelectBonus();
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(AND);
		sql.append(BONUS_CONSUMIDO);  
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(AND);
		sql.append(BONUS_ATIVADO); // fica ativo ate atingir 10 selos 
		sql.append(IGUAL_E_INTERROGACAO);
		System.out.println("Comando selecionarBonusNoConsumidoNoAtivado: " + sql);
		BonusCartaoFidelidadeDTO bunus = null;

		try (Connection con = conectarBDPostgree();
				PreparedStatement preparedStatement = con.prepareStatement(sql.toString());) {
			preparedStatement.setString(1, cliente.getTelefone());
			preparedStatement.setInt(2, BLOQUEADO_MENOR_10);
			preparedStatement.setInt(3, BLOQUEADO_MENOR_10);
			try (ResultSet rs = preparedStatement.executeQuery();) {
				while (rs.next()) {
					bunus = new BonusCartaoFidelidadeDTO();
					bunus.setIdBonus(rs.getInt(ID_BONUS));
					bunus.setTelefone(rs.getString(TELEFONE));
					bunus.setBonusConsumido(rs.getInt(BONUS_CONSUMIDO));
					bunus.setBonusAtivado(rs.getInt(BONUS_ATIVADO));
					bunus.setDataInclusao(rs.getDate(DATA_INCLUSAO));
					bunus.setDataVencimento(rs.getDate(DATA_VENCIMENTO));
					 
				}
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}

		return bunus;
	}

	private StringBuilder montarSelectCartao() {
		StringBuilder sql = new StringBuilder();

		sql.append(SELECT);
		sql.append(ID_PEDIDO);
		sql.append(VIRGULA);
		sql.append(ID_BONUS);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ESTADO_SELO_CARTAO);
		sql.append(VIRGULA);
		sql.append(DATA_INCLUSAO);
		sql.append(VIRGULA);
		sql.append(DATA_VENCIMENTO);
		sql.append(FROM);
		sql.append(TABELA_CARTAO_FIDELIDADE);
		return sql;
	}

	public List<CartaoFidelidadeDTO> selecionarCartaoFidelidade(BonusCartaoFidelidadeDTO bonus, PedidoDTO item) {
		List<CartaoFidelidadeDTO> listaCartao = new ArrayList<>();
		StringBuilder sql = montarSelectCartao();
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(AND);
		sql.append(ID_PEDIDO);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(AND);
		sql.append(ID_BONUS);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(AND);
		sql.append(ESTADO_SELO_CARTAO);
		sql.append(IGUAL_E_INTERROGACAO);
		System.out.println("Comando selecionarCartaoFidelidade: " + sql);

		try (Connection con = conectarBDPostgree();
				PreparedStatement preparedStatement = con.prepareStatement(sql.toString());) {
			preparedStatement.setString(1, item.getTelefone());
			preparedStatement.setInt(2, item.getIdPedido());
			preparedStatement.setInt(3, bonus.getIdBonus());
			preparedStatement.setInt(4, BLOQUEADO_MENOR_10);
			try (ResultSet rs = preparedStatement.executeQuery();) {
				while (rs.next()) {
					CartaoFidelidadeDTO cartao = new CartaoFidelidadeDTO();
					cartao.setIdPedido(rs.getInt(ID_PEDIDO));
					cartao.setIdBonus(rs.getInt(ID_BONUS));
					cartao.setTelefone(rs.getString(TELEFONE));
					cartao.setEstadoSeloCartao(rs.getInt(ESTADO_SELO_CARTAO));
					cartao.setDataInclusao(rs.getDate(DATA_INCLUSAO));
					cartao.setDataVencimento(rs.getDate(DATA_VENCIMENTO));
					listaCartao.add(cartao);
				}
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}

		return listaCartao;
	}
	

}
