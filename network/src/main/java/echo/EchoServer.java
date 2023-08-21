package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static final int PORT = 8000;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 1. TCP 소켓통신을 하기 위해 ServerSocket 객체를 생성
			// 서버소켓을 가지고 클라이언트의 소켓 접속을 기다릴 것
			serverSocket = new ServerSocket();
			
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT), 10);
			log("starts...[port:"+PORT+"]");
			
			while(true) {
				// 2. client로부터 연결요청이 올 때까지 계속 기다린다.(=main스레드 블락(wait))
				// 3. 연결요청이 들어오면 새로운 Socket 객체를 생성하여 Client의 Socket과 연결
				Socket socket = serverSocket.accept();
				new EchoRequestHandler(socket).start();
			}
		} catch (IOException e) {
			log("error:" + e);
		} finally {
			try {
				if(serverSocket != null && serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void log(String message) {
		System.out.println("[EchoServer#" + Thread.currentThread().getId() +"] " + message);
	}

}
