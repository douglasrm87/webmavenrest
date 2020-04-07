package webservicerest;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import postgree.dto.CartaoFidelidadeDTO;
import postgree.dto.ClienteDTO;
import web.EscopoAplicacao;

/*
<!-- 
Integrar heroku com Eclipse
https://devcenter.heroku.com/articles/deploying-java-applications-to-heroku-from-eclipse-or-intellij-idea  -->


 */

//http://localhost:8080/webmavenrest/rest/cartaofidelidade/123456

@Path("/cartaofidelidade")
public class CartaoFidelidadeWS {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String validarAcessoWS() {
		return "Web Service RestFul acessado com sucesso. Parabens.";
	}

	@GET
	@Path("/{cpf}")
	public String registrarItemCartaoFidelidade(@PathParam("cpf") String cpf) {
		boolean valida = false;
		int qdadeSelos = 0;
		for (ClienteDTO cliente : EscopoAplicacao.listaClienteCartaoFidelidade) {
			if (cpf.equals(cliente.getCpfCliente())) {
				cliente.getCartaoCliente().add(new CartaoFidelidadeDTO(new Date()));
				valida = true;
				qdadeSelos = cliente.getCartaoCliente().size();
				if (qdadeSelos == 10) {
					cliente.setCartaoCliente(new ArrayList<CartaoFidelidadeDTO>());
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
