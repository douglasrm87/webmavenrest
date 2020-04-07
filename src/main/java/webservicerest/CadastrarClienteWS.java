package webservicerest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import postgree.dto.ClienteDTO;
import web.EscopoAplicacao;

/*
<!-- 
Integrar heroku com Eclipse
https://devcenter.heroku.com/articles/deploying-java-applications-to-heroku-from-eclipse-or-intellij-idea  -->


 */

//http://localhost:8080/webmavenrest/rest/cadastrocliente
//localhost:8080/webmavenrest/rest/cadastrocliente/douglas/123

@Path("/cadastrocliente")
public class CadastrarClienteWS {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String validarAcessoWS() {
		return "Web Service RestFul cadastrocliente acessado com sucesso. Parabens.";
	}

	@GET
	@Path("/{nome}/{cpf}")
	public Response cadastrarCliente(@PathParam("nome") String nomeCliente,@PathParam("cpf") String cpf ) {
		for (ClienteDTO cliente : EscopoAplicacao.listaClienteCartaoFidelidade) {
			if (cliente.getCpfCliente().equalsIgnoreCase(cpf)) {
				return Response.status(200)
						.entity("Cliente ja cadastrado em:" + cliente.getDtCadastro())
						.build();
			}
		}
		EscopoAplicacao.listaClienteCartaoFidelidade.add(new ClienteDTO(cpf, nomeCliente)); 
		 return Response.status(200)
					.entity("Cliente cadastrado com sucesso." + new Date())
					.build();
	}

	@GET
	@Path("{year}/{month}/{day}")
	public Response getUserHistory(
			@PathParam("year") int year,
			@PathParam("month") int month, 
			@PathParam("day") int day) {

	   String date = year + "/" + month + "/" + day;

	   return Response.status(200)
		.entity("getUserHistory is called, year/month/day : " + date)
		.build();

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
