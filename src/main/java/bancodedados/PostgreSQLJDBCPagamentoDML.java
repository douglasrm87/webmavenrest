package bancodedados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.FormasPagamento;
import bancodedados.dto.PedidoDTO;

//http://brunorota.com.br/2012/05/19/tutorial-criar-crud-em-java-com-jdbc-parte-1-final/

// senha no pgadmin - 123456
// pgadmin: http://127.0.0.1:54654/browser/#

public class PostgreSQLJDBCPagamentoDML extends PostgreSQLJDBCDML {
	private static final String _5541992619070 = "5541992619070";
	private static final String REGISTRO_DUPLICADO = "23505";

	public static void main(String args[]) {
		new PostgreSQLJDBCPagamentoDML().processar();
	}

	private void processar() {

		int op = 0;
		do {
			System.out.println("1-Inserir Dados");
			System.out.println("2-Selecionar pedido e item");
			System.out.println("9-Sair do Sistema");
			System.out.println("Digite sua opção:");
			op = this.leia.nextInt();
			PedidoDTO selPedido;
			ClienteDTO cliente;
			PedidoDTO pedido;
			switch (op) {
			case 1:

				break;
			case 2:

				break;
			case 9:
				System.out.println("Saindo do sistema");
				break;
			default:
				break;
			}
		} while (op != 9);

	}

	public FormasPagamento selecionarPagamentoPedidoAbertoByTelefone(PedidoDTO pedido) {
		FormasPagamento formaSelecionado = null;
		StringBuilder sql = montarSelectPagamentoPedidoDTO();
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(AND);
		sql.append(ID_PEDIDO);
		sql.append(IGUAL_E_INTERROGACAO);

		System.out.println("Comando sql selecionar pedido por CPF: " + sql);
		try (Connection con = conectarBDPostgree();
				PreparedStatement preparedStatement = con.prepareStatement(sql.toString());) {
			preparedStatement.setString(1, pedido.getTelefone());
			preparedStatement.setInt(2, pedido.getIdPedido());
			try (ResultSet rs = preparedStatement.executeQuery();) {
				while (rs.next()) {
					formaSelecionado = new FormasPagamento();
					formaSelecionado.setIdPedido(rs.getInt(ID_PEDIDO));
					formaSelecionado.setTelefone(rs.getString(TELEFONE));
					formaSelecionado.setIdPagamento(rs.getInt(ID_FORMA_PAGAMENTO));
					formaSelecionado.setDataPedido(rs.getDate(DATA_REGISTRO));
				}
			}
			if (formaSelecionado != null)
				switch (formaSelecionado.getIdPagamento()) {
				case CentralMensagensBrewField.ID_TRANSFERENCIA_BANCARIA:
					formaSelecionado.setDescPagamento(CentralMensagensBrewField.TRANSF_BANCARIA);
					break;
				case CentralMensagensBrewField.ID_PICPAY:
					formaSelecionado.setDescPagamento(CentralMensagensBrewField.PICPAY);
					break;
				case CentralMensagensBrewField.ID_CARTAO_DEBITO:
					formaSelecionado.setDescPagamento(CentralMensagensBrewField.CARTAO_DEBITO);
					break;
				case CentralMensagensBrewField.ID_CARTAO_CREDITO:
					formaSelecionado.setDescPagamento(CentralMensagensBrewField.CARTAO_CREDITO);
					break;
				default:
					break;
				}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		return formaSelecionado;
	}

	private StringBuilder montarSelectPagamentoPedidoDTO() {
		StringBuilder sql = new StringBuilder();

		sql.append(SELECT);
		sql.append(ID_PEDIDO);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_FORMA_PAGAMENTO);
		sql.append(VIRGULA);
		sql.append(DATA_REGISTRO);
//		sql.append(VIRGULA);
//		sql.append(ESTADO_PEDIDO);

		sql.append(FROM);
		sql.append(TABELA_PAGAMENTO_DTO);
		return sql;
	}

	private String sqlInserirPagamentoPedidoDTO() {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(TABELA_PAGAMENTO_DTO);
		sql.append(ABRE_PARENTESES);

		sql.append(TELEFONE);
		sql.append(VIRGULA);

		sql.append(DATA_REGISTRO);
		sql.append(VIRGULA);

		sql.append(ID_PEDIDO);
		sql.append(VIRGULA);

		sql.append(ID_FORMA_PAGAMENTO);

		sql.append(FECHA_PARENTESES);
		sql.append(VALUES);
		sql.append(ABRE_PARENTESES);
		int tam = 4;
		for (int i = 0; i < tam; i++) {
			sql.append(INTERROGACAO);
			if (i < (tam - 1)) {
				sql.append(VIRGULA);
			}
		}
		sql.append(FECHA_PARENTESES);
		System.out.println("Comando insert: " + sql);
		return sql.toString();
	}

	public void cadastrarPagamentoPedidoDTO(FormasPagamento forma, PedidoDTO pedido) {

		String sqlCli = sqlInserirPagamentoPedidoDTO();
		try (Connection con = conectarBDPostgree();
				PreparedStatement pstmCli = con.prepareStatement(sqlCli.toString());) {
			pstmCli.setString(1, pedido.getTelefone());
			pstmCli.setDate(2, new Date(forma.getDataPedido().getTime()));
			pstmCli.setInt(3, pedido.getIdPedido());
			pstmCli.setInt(4, forma.getIdPagamento());
			pstmCli.execute();

		} catch (SQLException e) {
			if (REGISTRO_DUPLICADO.equals(e.getSQLState())) {
				System.out.println("****** Pagamento ja cadastrado. Vamos atualizar.Telefone: " + forma.getTelefone());
				atualizarPagamentoCliente(forma, pedido);
			} else {
				printSQLException(e, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int atualizarPagamentoCliente(FormasPagamento forma, PedidoDTO pedido) {
		int retorno = 0;

		StringBuilder sql = new StringBuilder();
		sql.append(UPDATE);
		sql.append(TABELA_PAGAMENTO_DTO);
		sql.append(SET);
		sql.append(ID_FORMA_PAGAMENTO);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(AND);
//		sql.append(ESTADO_PEDIDO);
//		sql.append(IGUAL_E_INTERROGACAO);
//		sql.append(AND);
		sql.append(ID_PEDIDO);
		sql.append(IGUAL_E_INTERROGACAO);

		System.out.println("Comando atualizar pedido: " + sql);
		try (Connection con = conectarBDPostgree(); PreparedStatement pstm = con.prepareStatement(sql.toString());) {
			pstm.setInt(1, forma.getIdPagamento());
			pstm.setString(2, pedido.getTelefone());
			pstm.setInt(3, pedido.getIdPedido());
			retorno = pstm.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
//	public int fecharPagamentoCliente(PedidoDTO pedido) {
//		int retorno = 0;
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(UPDATE);
//		sql.append(TABELA_PAGAMENTO_DTO);
//		sql.append(SET);
//		sql.append(ESTADO_PEDIDO);
//		sql.append(IGUAL_E_INTERROGACAO);
//		sql.append(WHERE);
//		sql.append(TELEFONE);
//		sql.append(IGUAL_E_INTERROGACAO);
//		sql.append(AND);
//		sql.append(ESTADO_PEDIDO);
//		sql.append(IGUAL_E_INTERROGACAO);
//		sql.append(AND);
//		sql.append(ID_PEDIDO);
//		sql.append(IGUAL_E_INTERROGACAO);
//
//		System.out.println("Comando atualizar pedido: " + sql);
//		try (Connection con = conectarBDPostgree(); PreparedStatement pstm = con.prepareStatement(sql.toString());) {
//			pstm.setInt(1, CentralMensagensBrewField.FECHADO);
//			pstm.setString(2, pedido.getTelefone());
//			pstm.setInt(3, CentralMensagensBrewField.ABERTO);
//			pstm.setInt(4, pedido.getIdPedido());
//			retorno = pstm.executeUpdate();
//
//		} catch (SQLException e) {
//			printSQLException(e, null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return retorno;
//	}

}
