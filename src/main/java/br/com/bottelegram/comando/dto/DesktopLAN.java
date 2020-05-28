package br.com.bottelegram.comando.dto;

public class DesktopLAN {
	private String pat;
	private String mac;
	private String broadcastlan;
	private String ipV4;
	private int porta;

	
	public int getPorta() {
		return this.porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public String getPat() {
		return this.pat;
	}

	public void setPat(String pat) {
		this.pat = pat;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getBroadcastlan() {
		return this.broadcastlan;
	}

	public void setBroadcastlan(String broadcastlan) {
		this.broadcastlan = broadcastlan;
	}

	public String getIpV4() {
		return this.ipV4;
	}

	public void setIpV4(String ipV4) {
		this.ipV4 = ipV4;
	}

}
