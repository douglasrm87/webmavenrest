package br.com.bottelegram.comando.telecom;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.bottelegram.comando.dto.DesktopLAN;

public class ComandoWakeOnLANXML  extends ComandoWakeOnLAN {
 
	/* Exemplo utilizado para carregar dados via XML, não utilizado neste momento */

	private List<DesktopLAN> carregarDadosArqXML(Reader fonte) {
		XStream stream = new XStream(new DomDriver());
		stream.alias("desktop", DesktopLAN.class);
		return (List<DesktopLAN>) stream.fromXML(fonte);
	}

	private List<DesktopLAN> carregarListaDesktopXML() {
		List<DesktopLAN> listaDesktopsLAN = null;
		try {
			FileReader reader = new FileReader(new java.io.File("desktopslan.xml"));
			listaDesktopsLAN = carregarDadosArqXML(reader);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaDesktopsLAN;
	}	
	private DesktopLAN localizarDesktopXML(String pat) {
		DesktopLAN pc = null;
		List<DesktopLAN> listaDesktopsLAN = carregarListaDesktopXML();
		for (int i = 0; i < listaDesktopsLAN.size(); i++) {
			if (pat.equalsIgnoreCase(listaDesktopsLAN.get(i).getPat())) {
				pc = listaDesktopsLAN.get(i);
				break;
			}
		}
		return pc;
	}

}
