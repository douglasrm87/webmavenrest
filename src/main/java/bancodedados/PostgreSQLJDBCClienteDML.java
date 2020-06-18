package bancodedados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodedados.dto.ClienteDTO;
import bancodedados.dto.EnderecoDTO;

//http://brunorota.com.br/2012/05/19/tutorial-criar-crud-em-java-com-jdbc-parte-1-final/

// senha no pgadmin - 123456
// pgadmin: http://127.0.0.1:54654/browser/#

public class PostgreSQLJDBCClienteDML extends PostgreSQLJDBCDML {
	private static final String _5541992619070 = "5541992619070";
	private static final String REGISTRO_DUPLICADO = "23505";

	public static void main(String args[]) {
		new PostgreSQLJDBCClienteDML().processar();
	}

	private void processar() {

		int op = 0;
		do {
			System.out.println("1-Inserir Dados");
			System.out.println("2-Selecionar todos os clientes");
			System.out.println("3-Selecionar cliente por Telefone");
			System.out.println("4-Atualizar dados cliente");
			System.out.println("5-Remover dados cliente");
			System.out.println("9-Sair do Sistema");
			System.out.println("Digite sua opção:");
			op = this.leia.nextInt();
			switch (op) {
			case 1:
				ClienteDTO cliente = new ClienteDTO("Douglas Mendes", _5541992619070, 1234567);
				EnderecoDTO endereco = new EnderecoDTO("80050300", "PR", "Jandaia do Sul", "Rei", "Rua", "Marmitas",
						"1010");
				cadastrarClienteDTO(cliente);
				inserirEnderecoDTO(cliente, endereco);

				break;
			case 2:

				List<ClienteDTO> listaCli = selecionarTodosClientes();
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

				break;
			case 3:

				ClienteDTO clienteDTO = selecionarClienteByTelefone(new ClienteDTO(_5541992619070));
				if (clienteDTO != null) {
					System.out.println("\n\nDados retornados:");
					System.out.println("ID Cliente: " + clienteDTO.getId());
					System.out.println("CPF: " + clienteDTO.getCpfCliente());
					System.out.println("Nome: " + clienteDTO.getNomeCliente());
					System.out.println("Data Registro: " + clienteDTO.getDtRegistro());
					System.out.println("Data Registro: " + clienteDTO.getTelefone());
					System.out.println("\n_____________________________________________");
				} else {
					System.out.println("\nCliente nÃ£o localizado.\n");
				}

				break;
			case 4:
 
				break;
			case 5:
				 
				break;
			case 9:
				System.out.println("Saindo do sistema");
				break;
			default:
				break;
			}
		} while (op != 9);

	}

	private StringBuilder montarSelectClienteDTO() {
		StringBuilder sql = new StringBuilder();

		sql.append(SELECT);
		sql.append(ID_CLIENTE);
		sql.append(VIRGULA);
		sql.append(CPF_CLIENTE);
		sql.append(VIRGULA);
		sql.append(NOME);
		sql.append(VIRGULA);
		sql.append(DATA_REGISTRO);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(PERMITE_EMAIL_SMS);
		sql.append(VIRGULA);
		sql.append(EMAIL);
		sql.append(VIRGULA);
		sql.append(EMPRESA);
		sql.append(VIRGULA);
		sql.append(ID_USUARIO_TELEGRAM);// id telegram

		sql.append(FROM);
		sql.append(TABELA_CLIENTE_DTO);
		return sql;
	}

	private StringBuilder montarSelectEndereco() {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT);
		sql.append(UF);
		sql.append(VIRGULA);
		sql.append(CIDADE);
		sql.append(VIRGULA);
		sql.append(BAIRRO);
		sql.append(VIRGULA);
		sql.append(TIPO_LOGRADOURO);
		sql.append(VIRGULA);
		sql.append(LOGRADOURO);
		sql.append(VIRGULA);
		sql.append(NUMERO);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(CEP);
		sql.append(FROM);
		sql.append(TABELA_ENDERECO_DTO);
		return sql;
	}

	private EnderecoDTO selecionarEnderecoByTelefone(EnderecoDTO endereco) {
		EnderecoDTO enderecoSelecionado = null;
		StringBuilder sql = montarSelectEndereco();
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL_E_INTERROGACAO);
		System.out.println("Comando sql selecionar endereço por CPF: " + sql);
		try (Connection con = conectarBDPostgree();
				PreparedStatement preparedStatement = con.prepareStatement(sql.toString());) {
			preparedStatement.setString(1, endereco.getTelefone());
			try (ResultSet rs = preparedStatement.executeQuery();) {
				while (rs.next()) {
					enderecoSelecionado = new EnderecoDTO();
					enderecoSelecionado.setUf(rs.getString(UF));
					enderecoSelecionado.setCidade(rs.getString(CIDADE));
					enderecoSelecionado.setBairro(rs.getString(BAIRRO));
					enderecoSelecionado.setTipo_logradouro(rs.getString(TIPO_LOGRADOURO));
					enderecoSelecionado.setLogradouro(rs.getString(LOGRADOURO));
					enderecoSelecionado.setNumero(rs.getString(NUMERO));
					enderecoSelecionado.setTelefone(rs.getString(TELEFONE));
					enderecoSelecionado.setCep(rs.getString(CEP));
				}
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		return enderecoSelecionado;
	}

	private EnderecoDTO selecionarEnderecoByTelefoneCliente(ClienteDTO cliente) {
		EnderecoDTO enderecoSelecionado = null;
		StringBuilder sql = montarSelectEndereco();
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL_E_INTERROGACAO);
		System.out.println("Comando sql selecionar endereço por CPF: " + sql);
		try (Connection con = conectarBDPostgree();
				PreparedStatement preparedStatement = con.prepareStatement(sql.toString());) {
			preparedStatement.setString(1, cliente.getTelefone());
			try (ResultSet rs = preparedStatement.executeQuery();) {
				while (rs.next()) {
					enderecoSelecionado = new EnderecoDTO();
					enderecoSelecionado.setUf(rs.getString(UF));
					enderecoSelecionado.setCidade(rs.getString(CIDADE));
					enderecoSelecionado.setBairro(rs.getString(BAIRRO));
					enderecoSelecionado.setTipo_logradouro(rs.getString(TIPO_LOGRADOURO));
					enderecoSelecionado.setLogradouro(rs.getString(LOGRADOURO));
					enderecoSelecionado.setNumero(rs.getString(NUMERO));
					enderecoSelecionado.setTelefone(rs.getString(TELEFONE));
					enderecoSelecionado.setCep(rs.getString(CEP));
				}
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		return enderecoSelecionado;
	}

	private ClienteDTO selecionarClienteByTelefone(ClienteDTO cliente) {
		ClienteDTO clienteSelecionado = null;
		StringBuilder sql = montarSelectClienteDTO();
		sql.append(WHERE);
		sql.append(TELEFONE);
		sql.append(IGUAL_E_INTERROGACAO);
		System.out.println("Comando sql selecionar cliente por CPF: " + sql);
		try (Connection con = conectarBDPostgree();
				PreparedStatement preparedStatement = con.prepareStatement(sql.toString());) {
			preparedStatement.setString(1, cliente.getTelefone());
			try (ResultSet rs = preparedStatement.executeQuery();) {
				while (rs.next()) {
					clienteSelecionado = new ClienteDTO();
					clienteSelecionado.setId(rs.getLong(ID_CLIENTE));
					clienteSelecionado.setCpfCliente(rs.getLong(CPF_CLIENTE));
					clienteSelecionado.setNomeCliente(rs.getString(NOME));
					clienteSelecionado.setDtRegistro(rs.getDate(DATA_REGISTRO));
					clienteSelecionado.setTelefone(rs.getString(TELEFONE));
					clienteSelecionado.setPermiteEmailSMS(rs.getString(PERMITE_EMAIL_SMS));
					clienteSelecionado.setEmail(rs.getString(EMAIL));
					clienteSelecionado.setIdEmpresa(rs.getLong(EMPRESA));
					clienteSelecionado.setIdUsuarioTelegram(rs.getLong(ID_USUARIO_TELEGRAM));// id telegram
				}
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		PostgreSQLJDBCPedidoDML ped = new PostgreSQLJDBCPedidoDML();
		cliente.setPedido(ped.selecionarPedidoAbertoByTelefone(cliente.getTelefone()));
		return clienteSelecionado;
	}

	public ClienteDTO selecionarClienteByIDTelegram(long idTelegram) {
		ClienteDTO clienteSelecionado = null;
		StringBuilder sql = montarSelectClienteDTO();
		sql.append(WHERE);
		sql.append(ID_USUARIO_TELEGRAM);
		sql.append(IGUAL_E_INTERROGACAO);
		System.out.println("Comando sql selecionar cliente por CPF: " + sql);
		try (Connection con = conectarBDPostgree();
				PreparedStatement preparedStatement = con.prepareStatement(sql.toString());) {
			preparedStatement.setLong(1, idTelegram);
			try (ResultSet rs = preparedStatement.executeQuery();) {
				while (rs.next()) {
					clienteSelecionado = new ClienteDTO();
					clienteSelecionado.setId(rs.getLong(ID_CLIENTE));
					clienteSelecionado.setCpfCliente(rs.getLong(CPF_CLIENTE));
					clienteSelecionado.setNomeCliente(rs.getString(NOME));
					clienteSelecionado.setDtRegistro(rs.getDate(DATA_REGISTRO));
					clienteSelecionado.setTelefone(rs.getString(TELEFONE));
					clienteSelecionado.setPermiteEmailSMS(rs.getString(PERMITE_EMAIL_SMS));
					clienteSelecionado.setEmail(rs.getString(EMAIL));
					clienteSelecionado.setIdEmpresa(rs.getLong(EMPRESA));
					clienteSelecionado.setIdUsuarioTelegram(rs.getLong(ID_USUARIO_TELEGRAM));// id telegram
				}
			}
			if (clienteSelecionado != null) {
				EnderecoDTO enderecoSelecionado = selecionarEnderecoByTelefoneCliente(clienteSelecionado);
				clienteSelecionado.setEndereco(enderecoSelecionado);
			}
		} catch (SQLException e) {
			printSQLException(e, null);
		}
		return clienteSelecionado;
	}

	private List<ClienteDTO> selecionarTodosClientes() {
		StringBuilder sql = montarSelectClienteDTO();
		List<ClienteDTO> listaCliente = new ArrayList<>();
		System.out.println("Comando sql selecionar todos clientes: " + sql);
		try (Connection con = conectarBDPostgree();
				PreparedStatement preparedStatement = con.prepareStatement(sql.toString());
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

	public void inserirEnderecoDTO(ClienteDTO cliente, EnderecoDTO endereco) {
		String sqlEnd = sqlInserirEnderecoDTO();
		try (Connection con = conectarBDPostgree();
				PreparedStatement pstmEnd = con.prepareStatement(sqlEnd.toString());) {
			pstmEnd.setString(1, endereco.getUf());
			pstmEnd.setString(2, endereco.getCidade());
			pstmEnd.setString(3, endereco.getBairro());
			pstmEnd.setString(4, endereco.getTipo_logradouro());
			pstmEnd.setString(5, endereco.getLogradouro());
			pstmEnd.setString(6, endereco.getNumero());
			pstmEnd.setString(7, cliente.getTelefone());// FK
			pstmEnd.setString(8, endereco.getCep());// FK
			pstmEnd.execute();

		} catch (SQLException e) {
//			SQLState: 23505
			printSQLException(e, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cadastrarClienteDTO(ClienteDTO cliente) {

		String sqlCli = sqlInserirClienteDTO();
		try (Connection con = conectarBDPostgree();
				PreparedStatement pstmCli = con.prepareStatement(sqlCli.toString());) {
			pstmCli.setLong(1, cliente.getCpfCliente());
			pstmCli.setString(2, cliente.getNomeCliente());
			pstmCli.setDate(3, new Date(cliente.getDtRegistro().getTime()));
			pstmCli.setString(4, cliente.getPermiteEmailSMS());
			pstmCli.setString(5, cliente.getEmail());
			pstmCli.setLong(6, cliente.getIdEmpresa());
			pstmCli.setString(7, cliente.getTelefone());
			pstmCli.setLong(8, cliente.getIdUsuarioTelegram());
			pstmCli.execute();

		} catch (SQLException e) {
			if (REGISTRO_DUPLICADO.equals(e.getSQLState())) {
				System.out.println("Telefone ja cadastrado. Nome: " + cliente.getNomeCliente() + " Telefone: "
						+ cliente.getTelefone());
			} else {
				printSQLException(e, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String sqlInserirClienteDTO() {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(TABELA_CLIENTE_DTO);
		sql.append(ABRE_PARENTESES);

		sql.append(CPF_CLIENTE);// cpf
		sql.append(VIRGULA);
		sql.append(NOME);// nome
		sql.append(VIRGULA);
		sql.append(DATA_REGISTRO);// data
		sql.append(VIRGULA);
		sql.append(PERMITE_EMAIL_SMS);
		sql.append(VIRGULA);
		sql.append(EMAIL);
		sql.append(VIRGULA);
		sql.append(EMPRESA);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(ID_USUARIO_TELEGRAM);
		sql.append(FECHA_PARENTESES);
		sql.append(VALUES);
		sql.append(ABRE_PARENTESES);
		int tam = 8;
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

	private String sqlInserirEnderecoDTO() {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_INTO);
		sql.append(TABELA_ENDERECO_DTO);
		sql.append(ABRE_PARENTESES);
		sql.append(UF);
		sql.append(VIRGULA);
		sql.append(CIDADE);
		sql.append(VIRGULA);
		sql.append(BAIRRO);
		sql.append(VIRGULA);
		sql.append(TIPO_LOGRADOURO);
		sql.append(VIRGULA);
		sql.append(LOGRADOURO);
		sql.append(VIRGULA);
		sql.append(NUMERO);
		sql.append(VIRGULA);
		sql.append(TELEFONE);
		sql.append(VIRGULA);
		sql.append(CEP);
		sql.append(FECHA_PARENTESES);
		sql.append(VALUES);
		sql.append(ABRE_PARENTESES);
		int tam = 8;
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

	 

	private int removerCliente(ClienteDTO cliente) {
		ClienteDTO clienteID = selecionarClienteByTelefone(cliente);
		int retorno = 0;
		if (clienteID != null) {
			StringBuilder sql = new StringBuilder();
			sql.append(DELETE_FROM);
			sql.append(TABELA_CLIENTE_DTO);
			sql.append(WHERE);
			sql.append(TELEFONE);
			sql.append(IGUAL);
			sql.append(INTERROGACAO);

			System.out.println("Comando delete: " + sql);

			try (Connection con = conectarBDPostgree();
					PreparedStatement pstm = con.prepareStatement(sql.toString());) {
				pstm.setString(1, clienteID.getTelefone());
				retorno = pstm.executeUpdate();

			} catch (SQLException e) {
				printSQLException(e, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	public int removerEndereco(EnderecoDTO end) {
		EnderecoDTO endereco = selecionarEnderecoByTelefone(end);
		int retorno = 0;
		if (endereco != null) {
			StringBuilder sql = new StringBuilder();
			sql.append(DELETE_FROM);
			sql.append(TABELA_ENDERECO_DTO);
			sql.append(WHERE);
			sql.append(TELEFONE);
			sql.append(IGUAL);
			sql.append(INTERROGACAO);

			System.out.println("Comando delete: " + sql);

			try (Connection con = conectarBDPostgree();
					PreparedStatement pstm = con.prepareStatement(sql.toString());) {
				pstm.setString(1, endereco.getTelefone());
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
