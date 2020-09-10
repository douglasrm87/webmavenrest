package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
Sucesso com CLI - WAR

Projeto esta no GIT do Heroku.
	git clone https://git.heroku.com/cliwarjavamaven.git
	

Suceso com CLI - WAR

SET NODE_EXTRA_CA_CERTS=D:\devstudio12\ConteudoHeroku\cliwarjavamaven\siteheroku.cer
keytool -import -alias heroku -keystore cacerts -file D:\devstudio12\ConteudoHeroku\cliwarjavamaven\siteheroku.cer
git config --global http.sslBackend "openssl"
git config --global http.sslCAInfo D:\devstudio12\ConteudoHeroku\cliwarjavamaven\siteheroku.cer

git clone https://git.heroku.com/cliwarjavamaven.git
mvn clean install
mvn package
D:\devstudio12\ConteudoHeroku\cliwarjavamaven>heroku login
D:\devstudio12\ConteudoHeroku\cliwarjavamaven>git init
D:\devstudio12\ConteudoHeroku\cliwarjavamaven>git config --global http.sslVerify false
D:\devstudio12\ConteudoHeroku\cliwarjavamaven>heroku git:remote -a cliwarjavamaven
D:\devstudio12\ConteudoHeroku\cliwarjavamaven>git add .
D:\devstudio12\ConteudoHeroku\cliwarjavamaven>git commit -am "upload do WAR"
D:\devstudio12\ConteudoHeroku\cliwarjavamaven>git push heroku master
			Para o deploy
heroku plugins:install java
heroku war:deploy target/projetojavawebmaven-0.0.1-SNAPSHOT.war --remote origin
heroku buildpacks:clear  --remote origin
Procfile - Talvez usar
web: java $JAVA_OPTS -Dserver.port=$PORT -Djavax.net.ssl.trustStore=heroku-truststore.jks -Djavax.net.ssl.trustStorePassword=changeit  -jar target/dependency/webapp-runner.jar  target/projetojavawebmaven-0.0.1-SNAPSHOT.war
heroku buildpacks:clear  --app cliwarjavamaven


 */

/* 
 * Certificado
 * https://devcenter.heroku.com/articles/ssl-endpoint
 * openssl rsa -in server.pass.key -out server.key
 * openssl genrsa -des3 -out server.pass.key 2048
 * set OPENSSL_CONF=C:\Program Files\\Git\\usr\\ssl\\openssl.cnf
 * openssl req -nodes -new -key server.key -out server.csr
 * openssl req -x509 -sha256 -newkey rsa:2048 -keyout server.key -out server.crt -days 1024 -nodes
 * 
 * https://github.com/heroku/cli/issues/1054
SET NODE_EXTRA_CA_CERTS=D:\devstudio12\workspace\javawebmaven\.jdk-overlay\jre\lib\security\herokucertificado.cer
git config --global http.sslBackend "openssl"
git config --global http.sslCAInfo D:\devstudio12\workspace\javawebmaven\.jdk-overlay\jre\lib\security\herokucertificado.cer

 * 
 * 
 * Sempre que mudar algo no pom.xml garantir que esta OK com mvn clean install 
 * https://devcenter.heroku.com/articles/getting-started-with-java#deploy-the-app
 * https://devcenter.heroku.com/articles/github-integration-review-apps
 * http://www.mauda.com.br/?p=1308
 C:\Program Files\heroku\bin>heroku login
 */

 // http://localhost:8080/webmavenheroku/postgree.jsf

@WebServlet("/servletdouglasrm87")
public class ExemploServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExemploServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int retorno = receberDadosHTML(request, response);
		switch (retorno) {
		case 0:
			request.getRequestDispatcher("WEB-INF/login.html").forward(request, response);
			break;
		case 1:
			request.getRequestDispatcher("WEB-INF/incluirlivro.html").forward(request, response);
			break;
		case 2:
			request.getRequestDispatcher("WEB-INF/logininvalido.html").forward(request, response);
			break;
		case 3:

			break;
		default:
			break;
		}
	}

	// https://www.devmedia.com.br/usando-html-forms-com-servlets/28533
	private int receberDadosHTML(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("name");
		String pass = request.getParameter("password");
		System.out.println("Nome: " + id);
		System.out.println("Password: " + pass);
		response.setContentType("text/html");
		RequestDispatcher rd = null;
		if ("teste".equals(id) && "teste".equals(pass)) {
			return 1;
		} else if ((id != null) && (pass != null)) {
			return 2;
		}
		return 0;

	}

}
