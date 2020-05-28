package br.com.bottelegram.comando.telecom;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.bottelegram.EscopoApplictCSCTimerTelegram;
import br.com.bottelegram.comando.dto.VPNConectada;
import br.com.bottelegram.comando.dto.VPNConectadaExcel;
import br.com.bottelegram.excel.PlanilhaExcelVPNConectada;

public class ComandoVPNConectada {

	public String localizarVPN(String matricula) {
		if (EscopoApplictCSCTimerTelegram.listaExcelVPNs == null) {
			EscopoApplictCSCTimerTelegram.listaExcelVPNs = PlanilhaExcelVPNConectada.carregarPlanilhaVPN();
		}
		List<VPNConectadaExcel> listaVPN = EscopoApplictCSCTimerTelegram.listaExcelVPNs; 
		String vpn = null;
		for (int i = 0; i < listaVPN.size(); i++) {
			if (matricula.equalsIgnoreCase(listaVPN.get(i).getMatricula())) {
				vpn = listaVPN.get(i).getOrigin();
				break;
			}
		}
		return vpn;
	}

	private List<VPNConectada> carregarDadosArqXML(Reader fonte) {
		XStream stream = new XStream(new DomDriver());
		stream.alias("vpnconectada", VPNConectada.class);
		return (List<VPNConectada>) stream.fromXML(fonte);
	}

	private List<VPNConectada> carregarListaVPN() {
		try {
			FileReader reader = new FileReader(new java.io.File("vpnconectada.xml"));
			List<VPNConectada> listaVPN = carregarDadosArqXML(reader);
			reader.close();
			return listaVPN;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		new ComandoVPNConectada().processar();
	}

	private void processar() {
		carregarListaVPN();
		System.out.println(localizarVPN("c049463"));
	}
}
