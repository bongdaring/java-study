package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ChatServer {
	private static final String HOST_ADDRESS = "127.0.0.1";
	private static final int HOST_PORT = 9999;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		List<PrintWriter> listPrintWriterList = new ArrayList<PrintWriter>();
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(HOST_ADDRESS, HOST_PORT), 10);
			log("chat server start");
			
			while(true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listPrintWriterList).start();
			}
		} catch (IOException e) {
			log("error:" + e);
		} finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				log("error:" + e);
			}
		}
	}
	
	private static void log(String message) {
		System.out.println("[ChatServer]"  + message);
	}
}
