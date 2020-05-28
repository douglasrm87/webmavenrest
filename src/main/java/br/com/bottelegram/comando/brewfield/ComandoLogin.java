package br.com.bottelegram.comando.brewfield;

import org.apache.log4j.Logger;

import bancodedados.dto.CentralMensagensBrewField;
import bancodedados.dto.ClienteDTO;
import bancodedados.dto.EnderecoDTO;
import bancodedados.dto.EnderecoXML;
import bancodedados.dto.LoginDTO;
import br.com.bottelegram.comando.dto.InteracaoComando;

public class ComandoLogin {

	private static final Logger logger = Logger.getLogger(ComandoLogin.class);

	public String processarLogin() {
		StringBuilder msg = new StringBuilder();
		msg.append(CentralMensagensBrewField.VOU_TE_CADASTRAR_DIGITE_NO_FORMATO);
		msg.append(CentralMensagensBrewField.CELULAR_CPF_CEP_NUMERP_COMPL);
		return msg.toString();
	}

	public String processarLogado(ClienteDTO cliente) {
		StringBuilder msg = new StringBuilder();
		msg.append(CentralMensagensBrewField.CLIENTE_JA_CADASTRADO);
		msg.append(CentralMensagensBrewField.NOME + cliente.getNomeCliente());
		msg.append(CentralMensagensBrewField.ESPACO);
		msg.append(CentralMensagensBrewField.ESPACO);
		msg.append(CentralMensagensBrewField.CELULAR + cliente.getTelefone());
		msg.append(CentralMensagensBrewField.ESPACO);
		msg.append(CentralMensagensBrewField.ESPACO);
		msg.append(CentralMensagensBrewField.CPF + cliente.getCpfCliente());
		msg.append(CentralMensagensBrewField.PULAR_LINHA);
		msg.append(CentralMensagensBrewField.CEP + cliente.getCep());
		msg.append(CentralMensagensBrewField.PULAR_LINHA);
		msg.append(CentralMensagensBrewField.ENDERECO + cliente.getEndereco());
		msg.append(CentralMensagensBrewField.PULAR_LINHA);
		msg.append(CentralMensagensBrewField.PODERA_ALTERAR_PARA_RECADASTRAR);
		msg.append(CentralMensagensBrewField.ALTERAR_DADOS_CLIENTE);
		msg.append(CentralMensagensBrewField.VOLTAR_MENU);
		return msg.toString();
	}

	public LoginDTO processarLogin(InteracaoComando dadosComando) {
		LoginDTO resLogin = new LoginDTO();
		StringBuilder msg = new StringBuilder();

		String strVet[] = dadosComando.getComplementoComando().split(",");
		if (strVet.length == 2) {
			msg.append(CentralMensagensBrewField.CADASTRADO_COM_SUCESSO);
			msg.append(CentralMensagensBrewField.NOME + dadosComando.getNomeUsuario() + " "
					+ dadosComando.getSobreNomeUsuario());
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			msg.append(CentralMensagensBrewField.CELULAR + dadosComando.getCliente().getTelefone());
			msg.append(CentralMensagensBrewField.PULAR_LINHA);
			msg.append(CentralMensagensBrewField.CEP + strVet[0]);
			EnderecoXML endXML = new EnderecoXML();
			EnderecoDTO endDTO = endXML.carregarDadosArqXML(strVet[0]);
			if (endDTO.getMensagem() != null && endDTO.getMensagem().length() > 0) {
				msg.append(endDTO.getMensagem());
				resLogin.setSucesso(false);
			} else {
				EnderecoDTO meuEndereco = new EnderecoDTO(endDTO.getUf(), endDTO.getCidade(), endDTO.getBairro(),
						endDTO.getTipo_logradouro(), endDTO.getLogradouro(), strVet[1]);
				msg.append(CentralMensagensBrewField.ENDERECO + meuEndereco);
				msg.append(CentralMensagensBrewField.PULAR_LINHA);
				dadosComando.getCliente().setEndereco(endDTO);
			}
		} else {
			msg.append(CentralMensagensBrewField.FORMATO_INCORRETO);
		}
		resLogin.setMensagem(msg.toString());
		return resLogin;
	}

}
