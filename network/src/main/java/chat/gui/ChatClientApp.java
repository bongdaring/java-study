package chat.gui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import chat.ChatClientThread;

public class ChatClientApp {
	private static final String HOST_ADDRESS = "127.0.0.1";
	private static final int HOST_PORT = 9999;
	
	public static void main(String[] args) {
		Socket socket = null;
		String name = null;
		Scanner scanner = null;

		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(HOST_ADDRESS, HOST_PORT));
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			scanner = new Scanner(System.in);
			
			while(true) {
				System.out.println("대화명을 입력하세요.");
				System.out.print("> ");
				name = scanner.nextLine();
				
				if (name.isBlank()) {
					System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
					continue;
				}
				pw.println("join:" + name);
				String checkJoin = br.readLine();
				
				if("join:ok".equals(checkJoin)) {
					new ChatWindow(name, socket, pw).show();
					break;
				} 
			}
		} catch (SocketException e) {
			log("error:" + e);
		} catch (IOException e) {
			log("error:" + e);
		} 
	}
	
	private static void log(String message) {
		System.out.println("[ChatClient]"  + message);
	}

}
