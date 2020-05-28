package br.com.bottelegram.comando.telecom;

import java.util.List;

import org.apache.commons.net.util.SubnetUtils;

import br.com.bottelegram.comando.dto.DesktopLAN;
import br.com.bottelegram.comando.dto.KeyServer;
import br.com.bottelegram.dhcppagina.DHCPLeasesKeyserver;

public class ComandoRede {
	public static final String MASCARA_BARRA_PADRAO_COPEL_NET = "/20";
	protected static final String PATRIMONIO_NAO_LOCALIZADO = "Patrimonio nao localizado: ";
	protected DesktopLAN localizarDesktopKEYServer(String patrimonio) {
		boolean valida = false;
		DHCPLeasesKeyserver keyServer = new DHCPLeasesKeyserver();
		List<KeyServer> listaLinha = keyServer.processar();
		DesktopLAN desktop = new DesktopLAN();

		for (KeyServer key : listaLinha) {
			if (key.getPatrimonio().contains(patrimonio)) {
				SubnetUtils utils = new SubnetUtils(key.getIpv4() + MASCARA_BARRA_PADRAO_COPEL_NET);
				desktop.setBroadcastlan(utils.getInfo().getBroadcastAddress());
				desktop.setMac(key.getMacAddress());
				desktop.setPat(patrimonio);
				desktop.setIpV4(key.getIpv4());
				valida = true;
			}
 
		}
		if (valida) {
			return desktop;
		}
		return null;
	}

}
