package test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

public class LocalHost {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
//			InetAddress[] test = inetAddress.getAllByName("168.126.63.1");
//			for(InetAddress inetAddress2 : test) {
//				System.out.println(inetAddress2.getHostName());
//			}
			String hostName = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();
			
			System.out.println(hostName);
			System.out.println(hostAddress);	
			
			byte[] IpAddresses = inetAddress.getAddress();
			for (byte IpAddress : IpAddresses) {
				System.out.println(IpAddress & 0x000000ff);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
