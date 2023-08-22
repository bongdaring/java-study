package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();
			
			// 채팅 서버에 추가하기 
			//1-1. FIN-WAIT -> TIME_WAIT 상태에서도 소켓 포트 할당이 가능하도록 하기 위해...
			serverSocket.setReuseAddress(true);
			
			// 2. 바인딩(Binding)
			// Socket에 InetSocketAddress(IPAddress + port)를 바인딩 한다.
			// IPAddress: 0.0.0.0: 특정 호스트 IP에 바인딩 하지 않는다. 
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 8090), 10);
			
			// 3. accept
			Socket socket = serverSocket.accept(); //blocking
			
			try {
				InetSocketAddress remoteInetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
				String remoteHostAddress = remoteInetSocketAddress.getAddress().getHostAddress();
				int remotePort = remoteInetSocketAddress.getPort();
				System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");
				
				// 4. IO Stream 받아오기
				OutputStream os = socket.getOutputStream();
				InputStream is = socket.getInputStream();
				
				while(true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking
					
					if(readByteCount == -1) {
						// 클라이언트가 정상적 종료(close() 호출)
						System.out.println("[server] closed by client");
						break;
					}
					
					// buffer을 0 ~ readByteCount까지 utf-8로 읽어라
					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] received:" + data);
					
					
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					// 6. 데이터 쓰기
					os.write(data.getBytes("utf-8"));
					
				}
			} catch (SocketException e) {
				System.out.println("[server] suddenly closed by client");
			} catch (IOException e) {
				System.out.println("[server] error : " + e);
			} finally {
				try {
					if(socket != null && !socket.isClosed()) {
						socket.close();											
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			System.out.println("[server] error:" + e);
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

}
