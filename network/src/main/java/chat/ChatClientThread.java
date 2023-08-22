package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;


public class ChatClientThread extends Thread {
private Socket socket;
	
	public ChatClientThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

			while(true) {
				String message = br.readLine();
				if(message == null) {
					break;
				}
				System.out.println(message);
			}
		} catch(SocketException e){
			log("error:" + e);
		} catch(IOException e){
			log("error:" + e);
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				log("error:" + e);
			}
		}
	}
	
	private static void log(String message) {
		System.out.println("[ChatClient]"  + message);
	}

}
