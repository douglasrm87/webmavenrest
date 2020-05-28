package br.com.bottelegram.comando.telecom;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

import br.com.bottelegram.comando.dto.DesktopLAN;

public class ComandoWakeOnLAN extends ComandoRede {
	public static final String MASCARA_PADRAO_COPEL_NET = "255.255.240.0";

	// http://www.jibble.org/wake-on-lan/
	public static void main(String[] args) {
		ComandoWakeOnLAN ligar = new ComandoWakeOnLAN();
//		ligar.ligarDesktop("L00000221");
		System.out.println(ligar.ligarDesktop("Douglas-PC"));
//		System.out.println(ligar.ligarDesktop("P475035"));

	}


	public String ligarDesktop(String desktop) {
		DesktopLAN computador = localizarDesktopKEYServer(desktop);
		StringBuilder resposta = new StringBuilder();
		if (computador != null) {
			computador.setPorta(9);
			ligar(computador);

		} else { // teste em casa
			computador = new DesktopLAN();
			computador.setBroadcastlan("192.168.15.255");
			computador.setMac("00-23-5A-73-D2-8B");
			computador.setPorta(9);
			ligar(computador);
		}
		resposta.append(PATRIMONIO_NAO_LOCALIZADO + desktop);
		return resposta.toString();

	}

	private String ligar(DesktopLAN computador) {
		StringBuilder resposta = new StringBuilder();

		System.out.println("Porta: " + computador.getPorta());
		System.out.println("MAC: " + computador.getMac());
		System.out.println("Broadcast: " + computador.getBroadcastlan());

		try (DatagramSocket socket = new DatagramSocket();) {
			byte[] macBytes = getMacBytes(computador.getMac());
			byte[] bytes = new byte[6 + 16 * macBytes.length];
			for (int i = 0; i < 6; i++) {
				bytes[i] = (byte) 0xff;
			}
			for (int i = 6; i < bytes.length; i += macBytes.length) {
				System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
			}

			InetAddress address = InetAddress.getByName(computador.getBroadcastlan());
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, computador.getPorta());
			socket.send(packet);
			resposta.append("Comando WOL enviado ao desktop:" + computador.getPat());
			resposta.append("Comando WOL enviado ao MAC:" + computador.getMac());
			resposta.append("Comando WOL enviado ao Porta:" + computador.getPorta());
		} catch (Exception e) {
			System.out.println("Falha noenvio dos pacotes para Wake-on-LANt:" + e.toString());
		}
		return resposta.toString();
	}

	private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
		byte[] bytes = new byte[6];
		String[] hex = macStr.split("(\\:|\\-)");
		if (hex.length != 6) {
			throw new IllegalArgumentException("MAC address inválido.");
		}
		try {
			for (int i = 0; i < 6; i++) {
				bytes[i] = (byte) Integer.parseInt(hex[i], 16);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"Dígitos hexadecimais Invalidos no MAC address recebido para ser ligado.");
		}
		return bytes;
	}
}
