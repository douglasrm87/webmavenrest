package br.com.bottelegram.dhcppagina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;

import br.com.bottelegram.comando.dto.KeyServer;

public class DHCPLeasesKeyserver {
	private static final String NAO_INFORMADO = "nao informado";
	private static final String FECHA_TR = "</tr>";
	private static final String ABRE_TR = "<tr>";
	private static final String KEYSERVER = "https://keyserver.copel.nt/lan/leases.php";

	public static void main(String[] args) {
		new DHCPLeasesKeyserver().processar();
	}

	public List<KeyServer> processar() {
		
		List<KeyServer> listaLinha = null;
		try {
			String res = carregarPaginaHTML();
			StringReader readerSTR = new StringReader(res);
			listaLinha = extractText(readerSTR);
//			for (KeyServer keyServer : listaLinha) {
//				System.out.println(keyServer);
//			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaLinha;
	}

	private List<KeyServer> extractText(Reader reader) throws IOException {
		BufferedReader br = new BufferedReader(reader);

		String line;
 
		List<KeyServer> listaKeyServer = new ArrayList<>();
		int contador = 1; 

		while ((line = br.readLine()) != null) {
			StringBuilder linhaHTML = new StringBuilder();
			if (line.contains(ABRE_TR)) {
				do {
					line = br.readLine();
					if (line == null) {
						break;
					}
					if ("<td></td>".equals(line.replaceAll("	", ""))) {
						line = NAO_INFORMADO;
					}
					linhaHTML.append(line);
					linhaHTML.append(";");
				} while (!line.contains(FECHA_TR));
				KeyServer keyServer = new KeyServer();
				String strVetor[] = linhaHTML.toString().split(";");
				keyServer.setIpv4(strVetor.length > 0 ? Jsoup.parse(strVetor[0]).text() : null);
				keyServer.setSubnetIP4(strVetor.length > 1 ? Jsoup.parse(strVetor[1]).text() : null);
				keyServer.setSubnetDescription(strVetor.length > 2 ? Jsoup.parse(strVetor[2]).text() : null);
				keyServer.setMacAddress(strVetor.length > 3 ? Jsoup.parse(strVetor[3]).text() : null);
				keyServer.setLeaseTime(strVetor.length > 4 ? Jsoup.parse(strVetor[4]).text() : null);
				keyServer.setExpiration(strVetor.length > 5 ? Jsoup.parse(strVetor[5]).text() : null);
				keyServer.setPatrimonio(strVetor.length > 6 ? Jsoup.parse(strVetor[6]).text() : null);
				keyServer.setState(strVetor.length > 7 ? Jsoup.parse(strVetor[7]).text() : null);
				keyServer.setVendor(strVetor.length > 8 ? Jsoup.parse(strVetor[8]).text() : null);
				listaKeyServer.add(keyServer);
				contador++;
			}
		}
		return listaKeyServer;
	}

	private String carregarPaginaHTML() {

		StringBuilder conteudoHTML = new StringBuilder();
		URL tUrl;
		InputStream tInput;

		String tLinha;

		try {
			tUrl = new URL(KEYSERVER);
			tInput = tUrl.openStream();

			try (InputStreamReader tArq1 = new InputStreamReader(tInput);
					BufferedReader tArq2 = new BufferedReader(tArq1);) {
				while (true) {
					tLinha = tArq2.readLine();
					if (tLinha == null) {
						break;
					}
					conteudoHTML.append(tLinha);
					conteudoHTML.append("\n");
				}
			} catch (MalformedURLException e1) {
				System.out.println("URL inválida: " + e1.getMessage());
			} catch (IOException e2) {
				System.out.println("Erro na obtenção do objeto: " + e2.getMessage());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Servidor Keyserver não localiza. Verificar se o acesso ao servidor é válido e liberado pela Internet.");
		}
		return conteudoHTML.toString();
	}
}