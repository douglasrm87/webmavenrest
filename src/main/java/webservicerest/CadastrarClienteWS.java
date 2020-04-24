package webservicerest;

import java.util.Date;

import bancodedados.dto.ClienteDTO;
import web.EscopoAplicacao;

/*
<!-- 
Integrar heroku com Eclipse
https://devcenter.heroku.com/articles/deploying-java-applications-to-heroku-from-eclipse-or-intellij-idea  -->


 */

//https://webmavenheroku.herokuapp.com/rest/cadastrocliente

public class CadastrarClienteWS {
	public String cadastrarCliente(String nomeCliente, int cpf) {
		StringBuilder sb = new StringBuilder();
		for (ClienteDTO cliente : EscopoAplicacao.listaClienteCartaoFidelidade) {
			if (cliente.getCpfCliente() == cpf) {
				sb.append("Cliente: <b>");
				sb.append(nomeCliente);
				sb.append("</b> como CPF: <b>");
				sb.append(cpf);
				sb.append("</b> ja cadastrado em: ");
				sb.append(cliente.getDtRegistro());
				return sb.toString();
			}
		}
		EscopoAplicacao.listaClienteCartaoFidelidade.add(new ClienteDTO(cpf, nomeCliente));
		sb.append("Cliente: <b>");
		sb.append(nomeCliente);
		sb.append("</b> como CPF: <b>");
		sb.append(cpf);
		sb.append("</b> foi cadastrado com sucesso: ");
		sb.append(new Date());
		return sb.toString();
	}
}
