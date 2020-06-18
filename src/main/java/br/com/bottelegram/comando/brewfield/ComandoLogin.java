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

//	public String processarLogin() {
//		StringBuilder msg = new StringBuilder();
//		msg.append(CentralMensagensBrewField.VOU_TE_CADASTRAR_DIGITE_NO_FORMATO);
//		msg.append(CentralMensagensBrewField.CELULAR_CPF_CEP_NUMERP_COMPL);
//		return msg.toString();
//	}

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
		msg.append(CentralMensagensBrewField.ENDERECO + cliente.getEndereco());
		msg.append(CentralMensagensBrewField.PULAR_LINHA);
		msg.append(CentralMensagensBrewField.PODERA_ALTERAR_PARA_RECADASTRAR);
//		msg.append(CentralMensagensBrewField.ALTERAR_DADOS_CLIENTE);
//		msg.append(CentralMensagensBrewField.VOLTAR_MENU);
		return msg.toString();
	}

	

}
