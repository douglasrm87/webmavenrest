package webservicerest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
/*
Para recolocar o Web service no ar.
heroku ps:restart --remote origin
heroku ps:restart web --remote origin



Sucesso com CLI - WAR

SET NODE_EXTRA_CA_CERTS=D:\devstudio12\ConteudoHeroku\cliwarjavamaven\siteheroku.cer
keytool -import -alias heroku -keystore cacerts -file D:\devstudio12\ConteudoHeroku\cliwarjavamaven\siteheroku.cer
git config --global http.sslBackend "openssl"
git config --global http.sslCAInfo D:\devstudio12\ConteudoHeroku\cliwarjavamaven\siteheroku.cer

git clone https://git.heroku.com/webmavenheroku.git
D:\devstudio12\workspace>heroku auth:token
 »   Warning: token will expire 04/07/2021
 »   Use heroku authorizations:create to generate a long-term token
d4fe8bec-19c4-4dda-b9f0-99985875e09f


mvn clean install
mvn package
heroku login
git init
git config --global http.sslVerify false
heroku git:remote -a webmavenheroku
git add .
git commit -am "versao 1.0"
git push heroku master

para acertar git
git pull origin master--allow-unrelated-histories
git remote add origin https://git.heroku.com/webmavenheroku.git

Para recolocar o Web service no ar.
heroku ps:restart --remote origin
heroku ps:restart web --remote origin

			Para o deploy
heroku plugins:install java
heroku war:deploy target/webmavenheroku-0.0.1-SNAPSHOT.war --remote origin
heroku buildpacks:clear  --remote origin

 */
//http://localhost:8080/webmavenrest/rest/centralservicos/1/123456/Douglas
//http://localhost:8080/webmavenrest/rest/centralservicos/1/123456/Douglas
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
	public Response cadastrarCliente(@PathParam("id") Integer id, @PathParam("cpf") int cpf,
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
