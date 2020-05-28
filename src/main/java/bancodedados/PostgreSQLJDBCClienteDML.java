package bancodedados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodedados.dto.ClienteDTO;

//http://brunorota.com.br/2012/05/19/tutorial-criar-crud-em-java-com-jdbc-parte-1-final/

// senha no pgadmin - 123456
// pgadmin: http://127.0.0.1:54654/browser/#

public class PostgreSQLJDBCClienteDML extends PostgreSQLJDBCDML {
	public static void main(String args[]) {
		new PostgreSQLJDBCClienteDML().processar();
	}

	private void processar() {
		try {
			Connection con = conectarBDPostgree();
			int op = 0;
			do {
				System.out.println("1-Inserir Dados");
				System.out.println("2-Selecionar todos os clientes");
				System.out.println("3-Selecionar cliente por CPF");
				System.out.println("4-Atualizar dados cliente");
				System.out.println("5-Remover dados cliente");
				System.out.println("9-Sair do Sistema");
				System.out.println("Digite sua opção:");
				op = this.leia.nextInt();
				switch (op) {
				case 1:
					if (con != null) {
						inserirCliente(con, new ClienteDTO(877554, "Douglas Mendes"));
					}
					break;
				case 2:
					if (con != null) {
						List<ClienteDTO> listaCli = selecionarTodosClientes(con);
						if (!listaCli.isEmpty()) {
							System.out.println("\n\nDados retornados:");
							for (ClienteDTO clienteDTO : listaCli) {
								System.out.println("ID Cliente: " + clienteDTO.getId());
								System.out.println("CPF: " + clienteDTO.getCpfCliente());
								System.out.println("Nome: " + clienteDTO.getNomeCliente());
								System.out.println("Data Registro: " + clienteDTO.getDtRegistro());
								System.out.println("\n_____________________________________________");
							}
						} else {
							System.out.println("\n\nNenhum cliente cadastrado.\n");
						}
					}
					break;
				case 3:
					if (con != null) {
						ClienteDTO clienteDTO = selecionarClienteByCPF(con, new ClienteDTO(877554));
						if (clienteDTO != null) {
							System.out.println("\n\nDados retornados:");
							System.out.println("ID Cliente: " + clienteDTO.getId());
							System.out.println("CPF: " + clienteDTO.getCpfCliente());
							System.out.println("Nome: " + clienteDTO.getNomeCliente());
							System.out.println("Data Registro: " + clienteDTO.getDtRegistro());
							System.out.println("\n_____________________________________________");
						} else {
							System.out.println("\nCliente não localizado.\n");
						}
					}
					break;
				case 4:
					if (con != null) {
						int ret = atualizarNomeCliente(con, new ClienteDTO(877554, "Douglas"));
						if (ret != 0) {
							System.out.println("\nRegistro alterado: " + ret);
						} else {
							System.out.println("\nRegistro não localizado: " + ret);
						}
						System.out.println("___________________________________________");
					}
					break;
				case 5:
					if (con != null) {
						int ret = removerCliente(con, new ClienteDTO(877554));
						if (ret != 0) {
							System.out.println("\nRegistro removido: " + ret);
						} else {
							System.out.println("\nRegistro não localizado: " + ret);
						}
						System.out.println("___________________________________________");
					}
					break;
				case 9:
					System.out.println("Saindo do sistema");
					break;
				default:
					break;
				}
			} while (op != 9);
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
		sql.append(ID_CLIENTE);
		sql.append(VIRGULA);
		sql.append(CPF_CLIENTE);
		sql.append(VIRGULA);
		sql.append(NOME);
		sql.append(VIRGULA);
		sql.append(DATA_REGISTRO);
		sql.append(FROM);
		sql.append(TABELA_CLIENTE);
		return sql;
	}

	private ClienteDTO selecionarClienteByCPF(Connection connection, ClienteDTO cliente) {
		ClienteDTO clienteSelecionado = null;
		StringBuilder sql = montarSelect();
		sql.append(WHERE);
		sql.append(CPF_CLIENTE);
		sql.append(IGUAL_E_INTERROGACAO);
		System.out.println("Comando sql selecionar cliente por CPF: " + sql);
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());) {
			preparedStatement.setLong(1, cliente.getCpfCliente());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				clienteSelecionado = new ClienteDTO();
				clienteSelecionado.setId(rs.getInt(ID_CLIENTE));
				clienteSelecionado.setCpfCliente(rs.getInt(CPF_CLIENTE));
				clienteSelecionado.setNomeCliente(rs.getString(NOME));
				clienteSelecionado.setDtRegistro(rs.getDate(DATA_REGISTRO));
			}
			rs.close();
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		return clienteSelecionado;
	}

	public List<ClienteDTO> selecionarTodosClientes(Connection connection) {
		StringBuilder sql = montarSelect();
		List<ClienteDTO> listaCliente = new ArrayList<>();
		System.out.println("Comando sql selecionar todos clientes: " + sql);
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();) {
			while (rs.next()) {
				ClienteDTO cliente = new ClienteDTO();
				cliente.setId(rs.getInt(ID_CLIENTE));
				cliente.setCpfCliente(rs.getInt(CPF_CLIENTE));
				cliente.setNomeCliente(rs.getString(NOME));
				cliente.setDtRegistro(rs.getDate(DATA_REGISTRO));
				listaCliente.add(cliente);
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		return listaCliente;
	}

	public void inserirCliente(Connection con, ClienteDTO cliente) {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(TABELA_CLIENTE);
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
			pstm.setLong(1, cliente.getCpfCliente());
			pstm.setString(2, cliente.getNomeCliente());
			pstm.setDate(3, new Date(cliente.getDtRegistro().getTime()));
			pstm.execute();

		} catch (SQLException e) {
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int removerCliente(Connection con, ClienteDTO cliente) {
		ClienteDTO clienteID = selecionarClienteByCPF(con, cliente);
		int retorno = 0;
		if (clienteID != null) {
			StringBuilder sql = new StringBuilder();
			sql.append(DELETE_FROM);
			sql.append(TABELA_CLIENTE);
			sql.append(WHERE);
			sql.append(CPF_CLIENTE);
			sql.append(IGUAL);
			sql.append(INTERROGACAO);

			System.out.println("Comando delete: " + sql);

			try (PreparedStatement pstm = con.prepareStatement(sql.toString());) {
				pstm.setLong(1, clienteID.getCpfCliente());
				retorno = pstm.executeUpdate();

			} catch (SQLException e) {
				printSQLException(e, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	public int atualizarNomeCliente(Connection con, ClienteDTO cliente) {
		int retorno = 0;
		ClienteDTO clienteID = selecionarClienteByCPF(con, cliente);
		if (clienteID != null) {
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE ");
			sql.append(TABELA_CLIENTE);
			sql.append(" SET ");
			sql.append(NOME);
			sql.append(IGUAL);
			sql.append(INTERROGACAO);
			sql.append(WHERE);
			sql.append(ID_CLIENTE);
			sql.append(IGUAL);
			sql.append(INTERROGACAO);

			System.out.println("Comando atualizar CPF: " + sql);
			try (PreparedStatement pstm = con.prepareStatement(sql.toString());) {
				pstm.setString(1, cliente.getNomeCliente());
				pstm.setLong(2, clienteID.getId());
				retorno = pstm.executeUpdate();

			} catch (SQLException e) {
				printSQLException(e, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
}
