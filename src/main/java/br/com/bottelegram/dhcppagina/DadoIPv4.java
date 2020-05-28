package br.com.bottelegram.dhcppagina;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.net.util.SubnetUtils;

public class DadoIPv4 {
	public static void main(String[] args) throws SocketException {

		SubnetUtils utils = new SubnetUtils("10.250.113.3/20");
		String[] allIps = utils.getInfo().getAllAddresses();
		System.out.println("Broadcast: " + utils.getInfo().getBroadcastAddress());

//		for (int i = 0; i < allIps.length; i++) {
//			System.out.println(allIps[i]);	
//		}

		// Modify according to your system
		/*
		 * NetworkInterface nif = NetworkInterface.getByIndex(1); List<InterfaceAddress>
		 * list = nif.getInterfaceAddresses();
		 * 
		 * for (InterfaceAddress iaddr : list) {
		 * 
		 * // getAddress() method System.out.println("getAddress() : " +
		 * iaddr.getAddress());
		 * 
		 * // getBroadcast() method System.out.println("getBroadcast() : " +
		 * iaddr.getBroadcast());
		 * 
		 * // getNetworkPrefixLength() mrthod System.out.println("PrefixLength : " +
		 * iaddr.getNetworkPrefixLength());
		 * 
		 * // hashCode() method System.out.println("Hashcode : " + iaddr.hashCode());
		 * 
		 * // toString() method System.out.println("toString() : " + iaddr.toString());
		 * 
		 * System.out.println("--------------------\n"); }
		 */
	}

}