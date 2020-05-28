package br.com.bottelegram.dhcppagina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Teste {
	private static final String NAO_INFORMADO = "nao informado";
	private static final String FECHA_TR = "</tr>";
	private static final String ABRE_TR = "<tr>";
	private static final String KEYSERVER = "https://keyserver.copel.nt/lan/leases.php";
	private static final int TAM_BUFFER = 2048;

	public static void main(String[] args) {

		String tUrlDig = "https://keyserver.copel.nt/lan/leases.php";
		URL tUrl;
		InputStream tInput;

		String tLinha;

		try {
			tUrl = new URL(tUrlDig);
			tInput = tUrl.openStream();

			try (InputStreamReader tArq1 = new InputStreamReader(tInput);
					BufferedReader tArq2 = new BufferedReader(tArq1);) {
				int contador = 1;
				while (true) {
					tLinha = tArq2.readLine();
					if (tLinha == null)
						break;
					System.out.println(contador+ " " +tLinha);
					contador ++;
				}
				 
			} catch (MalformedURLException e1) {
				System.out.println("URL inválida: " + e1.getMessage());
			} catch (IOException e2) {
				System.out.println("Erro na obtenção do objeto: " + e2.getMessage());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// método estático para a obtenção dos dados de uma determinada url
		private String getURLData(String url) {

			// cria um StringBuilder para armazenar a saída
			StringBuilder saida = new StringBuilder();
			try {
				// cria uma url com o endereço passado
				URL u = new URL(url);
				// abre a conexão com a url criada
				URLConnection uCon = u.openConnection();
				// obtém o input stream da conexão
				InputStream in = uCon.getInputStream();
				// um buffer para a leitura dos dados obtidos no input stream
				byte[] buffer = new byte[TAM_BUFFER];
				// tenta colocar dados dentro do buffer. enquanto existirem dados
				// (resultado da leitura diferentede -1), a execução continua
				int cont = 0;
				while (in.read(buffer) != -1) {
					// faz o append dos dados lidos na saida (StringBuilder)
					saida.append(new String(buffer));
//					System.out.println("Bufer: " + saida);
				}
				// fecha o input stream
				in.close();
				// tratamento de excessões...
			} catch (MalformedURLException exc) {
				saida.append("ERRO: URL mal formada.");
				exc.printStackTrace();
			} catch (IOException exc) {
				saida.append("IOException");
				exc.printStackTrace();
			} catch (SecurityException exc) {
				saida.append("ERRO: Não há permissão para conexão.");
				exc.printStackTrace();
			} catch (IllegalArgumentException exc) {
				saida.append("ERRO: O proxy é null ou de tipo incorreto.");
				exc.printStackTrace();
			} catch (UnsupportedOperationException exc) {
				saida.append("ERRO: A subclasse que implementa o protocolo não suporta este método.");
				exc.printStackTrace();
			}
			return saida.toString();
		}
}
