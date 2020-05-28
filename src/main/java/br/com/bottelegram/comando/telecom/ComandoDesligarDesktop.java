package br.com.bottelegram.comando.telecom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ComandoDesligarDesktop {
	public static void main(String[] args) {
		ComandoDesligarDesktop c = new ComandoDesligarDesktop();
		c.processar();

	}

	private void processar() {
		try {
			DatagramSocket server = new DatagramSocket();
			byte[] buf;
			DatagramPacket dgp;

			buf = new byte[1000];
			dgp = new DatagramPacket(buf, buf.length);
			int porta = 5000;
			InetAddress ip;

			ip = InetAddress.getByName("p475034");

			boolean livre = false;
			do {
				try {
					server = new DatagramSocket(porta, ip);
					server.receive(dgp);
					server.send(dgp);
					livre = true;
				} catch (BindException e) {
					porta++;
					e.printStackTrace();
				} catch (SocketException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while (!livre);
			System.out.println("Porta conectada:" + porta);
			server.close();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		
//		server.receive(dgp);
	}


	private crunchifyResultFromCommand getStreamResult(InputStream inputStream, String type) {
		return new crunchifyResultFromCommand(inputStream, type);
	}

	private void executeCommand(final String command) throws IOException {
		// Returns the runtime object associated with the current Java application.
		Runtime crunchifyRuntime = Runtime.getRuntime();

		crunchifyResultFromCommand crunchifyError, crunchifyResult;
		Process proc1 = crunchifyRuntime.exec(command);
		try (InputStream erro = proc1.getErrorStream(); InputStream retorno = proc1.getInputStream();) {

			crunchifyError = getStreamResult(erro, "ERROR");

			crunchifyResult = getStreamResult(retorno, "OUTPUT");
			crunchifyError.start();
			crunchifyResult.start();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Signals that an I/O exception of some sort has occurred.
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	private class crunchifyResultFromCommand extends Thread {
		InputStream inputStream = null;

		// This abstract class is the superclass of all classes representing an input
		// stream of bytes.
		crunchifyResultFromCommand(InputStream is, String type) {
			this.inputStream = is;
		}

		public void run() {
			String crunchifyString = null;
			try {

				// Reads text from a character-input stream, buffering characters so as to
				// provide for the efficient reading of characters, arrays, and lines.
				BufferedReader br = new BufferedReader(new InputStreamReader(this.inputStream));
				while ((crunchifyString = br.readLine()) != null) {
					System.out.println("Resultado ping: " + crunchifyString);
					System.out.println("Resultado ping: " + crunchifyString);
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	private StringBuilder aplicaComandodesligar(String comando) {
	StringBuilder resposta = new StringBuilder();
	System.out.println("aplicaComandoPing comando: " + comando);
	try {
		Scanner pingar = new Scanner(Runtime.getRuntime().exec(comando).getInputStream());
		System.out.println("aplicaComandoPing apos Scanner: " + pingar.toString());
		System.out.println("aplicaComandoPing apos Scanner: " + pingar.next());
		String respPing = "";
		while (pingar.hasNextLine()) {
			respPing = pingar.nextLine();
		 
		}
		pingar.close();
	} catch (IOException ex) {
		System.out.println(ex.getMessage());
	}
	
	System.out.println("Resultado Ping: " + resposta);
	return resposta;
}

}
