package webservicerest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/t")
public class Teste {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String validarAcessoWS() {
		return "Web Service RestFul acessado com sucesso. Parabéns.";
	}

	@GET
	@Path("/{cpf}")
	public String registrarItemCartaoFidelidade(@PathParam("cpf") String cpf) {
		return "Registrado item cartao fidelidade do CPF " + cpf + " com sucesso." + ".";
	}

}
// The request http://example.com/api/orders/345 will return "returning order with id 345".
/*
 * @ApplicationPath("api") public class MyRestApp extends Application { }
 * 
	@Produces(MediaType.APPLICATION_JSON)
 
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
