package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			try {
				System.out.print(">> ");
				String line = scanner.nextLine();
				
				if("quit".equals(line)) {
					break;
				}
				
				InetAddress inetAddress = InetAddress.getLocalHost();
				InetAddress[] inetAddresses = inetAddress.getAllByName(line);
				for(InetAddress inetAddress2 : inetAddresses) {
					System.out.println(
							line +":"+ inetAddress2.getHostAddress());
				}
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}

	}

}
