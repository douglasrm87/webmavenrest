package webservicerest;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

/*
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

 

			Para o deploy
heroku plugins:install java
heroku war:deploy target/webmavenheroku-0.0.1-SNAPSHOT.war --remote origin
heroku buildpacks:clear  --remote origin
 */
//http://localhost:8080/webmavenrest/rest/cartaofidelidade
//http://localhost:8080/webmavenrest/rest/cartaofidelidade/123

//http://localhost:8080/webmavenrest/rest/centralservicos/1/123456/Douglas

//https://www.logicbig.com/tutorials/java-ee-tutorial/jax-rs/path-annotion-resource-mapping.html
@ApplicationPath("rest")
public class WSRestBase extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		return Collections.emptySet();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String validarAcessoWSSuperClasse() {
		return "Web Service - WSRestBase - RestFul acessado com sucesso. Parabéns.";
	}
}
