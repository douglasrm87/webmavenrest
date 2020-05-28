package br.com.bottelegram.comando.dto;

public class KeyServer {
	private String ipv4;
	private String subnetIP4;
	private String subnetDescription;
	private String macAddress;
	private String leaseTime;
	private String expiration;
	private String patrimonio;
	private String state;
	private String vendor;

	public String getIpv4() {
		return this.ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	public String getSubnetIP4() {
		return this.subnetIP4;
	}

	public void setSubnetIP4(String subnetIP4) {
		this.subnetIP4 = subnetIP4;
	}

	public String getSubnetDescription() {
		return this.subnetDescription;
	}

	public void setSubnetDescription(String subnetDescription) {
		this.subnetDescription = subnetDescription;
	}

	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getLeaseTime() {
		return this.leaseTime;
	}

	public void setLeaseTime(String leaseTime) {
		this.leaseTime = leaseTime;
	}

	public String getExpiration() {
		return this.expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getPatrimonio() {
		return this.patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "KeyServer [ipv4=" + this.ipv4 + ", subnetIP4=" + this.subnetIP4 + ", subnetDescription="
				+ this.subnetDescription + ", macAddress=" + this.macAddress + ", leaseTime=" + this.leaseTime
				+ ", expiration=" + this.expiration + ", patrimonio=" + this.patrimonio + ", state=" + this.state
				+ ", vendor=" + this.vendor + "]";
	}

}
