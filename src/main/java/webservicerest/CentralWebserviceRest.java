package webservicerest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

//http://localhost:8080/webmavenheroku/rest/centralservicos/1/123456/Douglas
@Path("/centralservicos")
public class CentralWebserviceRest {

	private final static String[] urls = { "http://localhost:8080/webmavenheroku",
			"http://localhost:8080/webmavenheroku/rest/centralservicos/1/123456/Douglas",
			"https://webmavenheroku.herokuapp.com/rest/centralservicos/2/123456/Douglas" };

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String validarAcessoCentralServicos() {
		StringBuilder sb = new StringBuilder();
		sb.append("Central DRM87 Web Service RestFul.");
		sb.append(" <br> URLs Validas:");
		for (int i = 0; i < urls.length; i++) {
			sb.append("  <br> ");
			sb.append(urls[i]);
		}

		sb.append("  <br> ");
		sb.append("Sistema Operacional: ");
		sb.append(iswindows());
		sb.append("  <br> ");
		return sb.toString();
	}

	private String iswindows() {
		// So: Windows 7
		String so = System.getProperty("os.name");
		System.out.println("So: " + so);
		return so;

	}

	@GET
	@Path("/{id}/{cpf}/{nome}")
	public Response cadastrarCliente(@PathParam("id") Integer id, @PathParam("cpf") String cpf,
			@PathParam("nome") String nomeCliente) {

		String retorno = null;
		switch (id) {
		case 1:
			CadastrarClienteWS cad = new CadastrarClienteWS();
			retorno = cad.cadastrarCliente(nomeCliente, cpf);
			break;
		case 2:
			CartaoFidelidadeWS car = new CartaoFidelidadeWS();
			retorno = car.registrarItemCartaoFidelidade(cpf);
			break;
		default:
			retorno = validarAcessoCentralServicos();

		}
		ResponseBuilder response = Response.status(200).entity(retorno + " - " + new Date());
		return response.build();
	}

}
