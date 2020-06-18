package bancodedados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.ItemPedidoDTO;
import bancodedados.dto.PedidoDTO;

//http://brunorota.com.br/2012/05/19/tutorial-criar-crud-em-java-com-jdbc-parte-1-final/

// senha no pgadmin - 123456
// pgadmin: http://127.0.0.1:54654/browser/#

public class PostgreSQLJDBCPedidoDML extends PostgreSQLJDBCDML {
	private static final String _5541992619070 = "5541992619070";
	private static final String REGISTRO_DUPLICADO = "23505";

	public static void main(String args[]) {
		new PostgreSQLJDBCPedidoDML().processar();
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
				cliente = new ClienteDTO("Douglas Mendes", _5541992619070, 1234567);
				pedido = new PedidoDTO(_5541992619070, 0, 0);
				cadastrarPedidoDTO(pedido);
				selPedido = selecionarPedidoAbertoByTelefone(_5541992619070);
				ItemPedidoDTO item = new ItemPedidoDTO("APA", 18, _5541992619070 );
				cadastrarItemPedidoDTO(item, selPedido);

				break;
			case 2:
				cliente = new ClienteDTO("Douglas Mendes", _5541992619070, 1234567);
				pedido = new PedidoDTO(_5541992619070, 0, 0);
				selPedido = selecionarPedidoAbertoByTelefone(_5541992619070);
				selecionarItemPedidoAbertoByTelefone(selPedido);

				break;
			case 9:
				System.out.println("Saindo do sistema");
				break;
			default:
				break;
			}
		} while (op != 9);

	}

	public List<ItemPedidoDTO> selecionarItemPedidoAbertoByTelefone(PedidoDTO pedido) {
		ItemPedidoDTO itemPedidoSelecionado = null;
		List<ItemPedidoDTO> listaItens = new ArrayList<>();
		StringBuilder sql = montarSelectItemPedidoDTO();
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(AND);
		sql.append(ID_PEDIDO);
		sql.append(IGUAL_E_INTERROGACAO);

		System.out.println("Comando sql selecionar item pedido por : " + sql);
		try (Connection con = conectarBDPostgree();
				PreparedStatement preparedStatement = con.prepareStatement(sql.toString());) {
			preparedStatement.setString(1, pedido.getTelefone());
			preparedStatement.setInt(2, pedido.getIdPedido());
			try (ResultSet rs = preparedStatement.executeQuery();) {
				while (rs.next()) {
					itemPedidoSelecionado = new ItemPedidoDTO();
					itemPedidoSelecionado.setTelefone(rs.getString(TELEFONE));
					itemPedidoSelecionado.setValorCerveja(rs.getDouble(VALOR_ITEM_PEDIDO));
					itemPedidoSelecionado.setDataPedido(rs.getDate(DATA_REGISTRO));
					itemPedidoSelecionado.setEstiloCerveja(rs.getString(DESCRICAO_ESTILO));
					listaItens.add(itemPedidoSelecionado);
				}
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		return listaItens;
	}

	public PedidoDTO selecionarPedidoAbertoByTelefone(String telefone) {
		PedidoDTO pedidoSelecionado = null;
		StringBuilder sql = montarSelectPedidoDTO();
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(AND);
		sql.append(ESTADO_PEDIDO);
		sql.append(IGUAL_E_INTERROGACAO);

		System.out.println("Comando sql selecionar pedido por CPF: " + sql);
		try (Connection con = conectarBDPostgree();
				PreparedStatement preparedStatement = con.prepareStatement(sql.toString());) {
			preparedStatement.setString(1, telefone);
			preparedStatement.setInt(2, CentralMensagensBrewField.ABERTO);
			try (ResultSet rs = preparedStatement.executeQuery();) {
				while (rs.next()) {
					pedidoSelecionado = new PedidoDTO();
					pedidoSelecionado.setIdPedido(rs.getInt(ID_PEDIDO));
					pedidoSelecionado.setTelefone(rs.getString(TELEFONE));
					pedidoSelecionado.setValorTotalPedido(rs.getDouble(VALOR_PEDIDO));
					pedidoSelecionado.setDataPedido(rs.getDate(DATA_REGISTRO));
					pedidoSelecionado.setEstadoPedido(rs.getInt(ESTADO_PEDIDO));
				}
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		return pedidoSelecionado;
	}

	private StringBuilder montarSelectItemPedidoDTO() {
		StringBuilder sql = new StringBuilder();

		sql.append(SELECT);

		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(VALOR_ITEM_PEDIDO);
		sql.append(VIRGULA);
		sql.append(DATA_REGISTRO);
		sql.append(VIRGULA);
		sql.append(DESCRICAO_ESTILO);

		sql.append(FROM);
		sql.append(TABELA_ITEM_PEDIDO_DTO);
		return sql;
	}

	private StringBuilder montarSelectPedidoDTO() {
		StringBuilder sql = new StringBuilder();

		sql.append(SELECT);
		sql.append(ID_PEDIDO);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(VALOR_PEDIDO);
		sql.append(VIRGULA);
		sql.append(DATA_REGISTRO);
		sql.append(VIRGULA);
		sql.append(ESTADO_PEDIDO);

		sql.append(FROM);
		sql.append(TABELA_PEDIDO_DTO);
		return sql;
	}

	public void cadastrarPedidoDTO(PedidoDTO pedido) {

		String sqlCli = sqlInserirPedidoDTO();
		try (Connection con = conectarBDPostgree();
				PreparedStatement pstmCli = con.prepareStatement(sqlCli.toString());) {
			pstmCli.setString(1, pedido.getTelefone());
			pstmCli.setDouble(2, pedido.getValorTotalPedido());
			pstmCli.setDate(3, new Date(pedido.getDataPedido().getTime()));
			pstmCli.setInt(4, pedido.getEstadoPedido());
			pstmCli.execute();

		} catch (SQLException e) {
			if (REGISTRO_DUPLICADO.equals(e.getSQLState())) {
				System.out.println("Pedido ja cadastrado.  Telefone: " + pedido.getTelefone());
			} else {
				printSQLException(e, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String sqlInserirPedidoDTO() {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(TABELA_PEDIDO_DTO);
		sql.append(ABRE_PARENTESES);

		sql.append(TELEFONE);
		sql.append(VIRGULA);

		sql.append(VALOR_PEDIDO);
		sql.append(VIRGULA);
		sql.append(DATA_REGISTRO);
		sql.append(VIRGULA);
		sql.append(ESTADO_PEDIDO);

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

	private String sqlInserirItemPedidoDTO() {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(TABELA_ITEM_PEDIDO_DTO);
		sql.append(ABRE_PARENTESES);

		sql.append(TELEFONE);
		sql.append(VIRGULA);

		sql.append(VALOR_ITEM_PEDIDO);
		sql.append(VIRGULA);

		sql.append(DATA_REGISTRO);
		sql.append(VIRGULA);

		sql.append(DESCRICAO_ESTILO);
		sql.append(VIRGULA);
 		sql.append(ID_PEDIDO);

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
		System.out.println("Comando insert: " + sql);
		return sql.toString();
	}

	private int removerPedido(PedidoDTO ped) {

		int retorno = 0;

		StringBuilder sql = new StringBuilder();
		sql.append(DELETE_FROM);
		sql.append(TABELA_PEDIDO_DTO);
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL);
		sql.append(INTERROGACAO);

		System.out.println("Comando delete: " + sql);

		try (Connection con = conectarBDPostgree(); PreparedStatement pstm = con.prepareStatement(sql.toString());) {
			pstm.setString(1, ped.getTelefone());
			retorno = pstm.executeUpdate();

		} catch (SQLException e) {
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public void cadastrarItemPedidoDTO(ItemPedidoDTO item, PedidoDTO pedido) {

		String sqlCli = sqlInserirItemPedidoDTO();
		try (Connection con = conectarBDPostgree();
				PreparedStatement pstmCli = con.prepareStatement(sqlCli.toString());) {
			pstmCli.setString(1, item.getTelefone());
			pstmCli.setDouble(2, item.getValorCerveja());
			pstmCli.setDate(3, new Date(item.getDataPedido().getTime()));
			pstmCli.setString(4, item.getEstiloCerveja());
 
			pstmCli.setInt(5, pedido.getIdPedido());
			pstmCli.execute();

		} catch (SQLException e) {
			if (REGISTRO_DUPLICADO.equals(e.getSQLState())) {
				System.out.println("Pedido ja cadastrado. Telefone: " + item.getTelefone());
			} else {
				printSQLException(e, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int removerItemPedidoDTO(ItemPedidoDTO item, PedidoDTO ped) {

		int retorno = 0;

		StringBuilder sql = new StringBuilder();
		sql.append(DELETE_FROM);
		sql.append(TABELA_ITEM_PEDIDO_DTO);
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL);
		sql.append(INTERROGACAO);
		sql.append(AND);
		sql.append(ID_PEDIDO);
		sql.append(IGUAL);
		sql.append(INTERROGACAO);
		sql.append(AND);
		sql.append(DESCRICAO_ESTILO);
		sql.append(IGUAL);
		sql.append(INTERROGACAO);
		

		System.out.println("Comando delete item: " + sql);

		try (Connection con = conectarBDPostgree(); PreparedStatement pstm = con.prepareStatement(sql.toString());) {
			pstm.setString(1, ped.getTelefone());
			pstm.setInt(2, ped.getIdPedido());
			pstm.setString(3, item.getEstiloCerveja());
			retorno = pstm.executeUpdate();

		} catch (SQLException e) {
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}
	public int fecharPedidoCliente(ClienteDTO cliente) {

		
		int retorno = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(UPDATE);
		sql.append(TABELA_PEDIDO_DTO);
		sql.append(SET);
		sql.append(ESTADO_PEDIDO);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(VIRGULA);
		sql.append(VALOR_PEDIDO);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL_E_INTERROGACAO);
		sql.append(AND);
		sql.append(ESTADO_PEDIDO);
		sql.append(IGUAL_E_INTERROGACAO);

		double totalPedido = 0.0;
		for (ItemPedidoDTO item : cliente.getPedido().getListaItens()) {
			totalPedido += item.getValorCerveja();
		}
		if (totalPedido < CentralMensagensBrewField.LIMIAR_TAXA_ENTREGA) {
			totalPedido += CentralMensagensBrewField.TAXA_ENTREGA_MENOR_100;
		}
		
		System.out.println("Comando atualizar pedido: " + sql);
		try (Connection con = conectarBDPostgree(); PreparedStatement pstm = con.prepareStatement(sql.toString());) {
			pstm.setInt(1, CentralMensagensBrewField.FECHADO);
			pstm.setDouble(2, totalPedido);
			pstm.setString(3, cliente.getTelefone());
			pstm.setInt(4, CentralMensagensBrewField.ABERTO);
			retorno = pstm.executeUpdate();

		} catch (SQLException e) {
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return retorno;
	}
 
}
