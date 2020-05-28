package webservicerest;

import java.util.ArrayList;
import java.util.Date;

import bancodedados.dto.CartaoFidelidadeDTO;
import bancodedados.dto.ClienteDTO;
import web.EscopoAplicacao;

/*
<!-- 
Integrar heroku com Eclipse
https://devcenter.heroku.com/articles/deploying-java-applications-to-heroku-from-eclipse-or-intellij-idea  -->


 */

//http://localhost:8080/webmavenheroku/rest/cartaofidelidade/123456

 
public class CartaoFidelidadeWS {
 
	public String registrarItemCartaoFidelidade(int cpf) {
		boolean valida = false;
		int qdadeSelos = 0;
		for (ClienteDTO cliente : EscopoAplicacao.listaClienteCartaoFidelidade) {
			if (cpf == cliente.getCpfCliente()) {
				cliente.adicionarCartaoFidelidade( new CartaoFidelidadeDTO(new Date(),cpf));
				valida = true;
				qdadeSelos = cliente.obterBonusCartaoFidelidade();
				if (qdadeSelos == 10) {
					cliente.adicionarCartaoFidelidade( new CartaoFidelidadeDTO());
					return "Cliente atingiu 10 Selos. CPF:" + cpf + " - Consumo gratis.";
				}
			}
		}
		if (valida) {
			return "Registrado item cartao fidelidade do CPF " + cpf + " com sucesso." + " Saldo atual: " + qdadeSelos;
		}
		return "Cliente nao localizado. CPF: " + cpf;

	}

}
// The request http://example.com/api/orders/345 will return "returning order with id 345".
/*
 * @ApplicationPath("api") public class MyRestApp extends Application { }
 * 
 * @Produces(MediaType.APPLICATION_JSON)
 * 
 * @Path("/orders") public class Order {
 * 
 * @GET public String getOrders() { return "returning all orders"; }
 * 
 * @GET
 * 
 * @Path("{orderId}") public String getOrderById(
 * 
 * @PathParam("orderId") String orderId) { return "return order with id " +
 * orderId; } }
 */
