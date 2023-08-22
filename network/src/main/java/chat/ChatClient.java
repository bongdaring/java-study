package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;


public class ChatClient {
	private static final String HOST_ADDRESS = "127.0.0.1";
	private static final int HOST_PORT = 9999;

	public static void main(String[] args) {
		Socket socket = null;
		Scanner scanner = null;
		
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(HOST_ADDRESS, HOST_PORT));
			log("채팅 서버에 연결되었습니다...");
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			scanner = new Scanner(System.in);
			
			while(true) {
				System.out.print("닉네임>>");
				pw.println("join:" + scanner.nextLine());
				
				String checkJoin = br.readLine();
				
				// 닉네임 공백은 서버에서 처리해보기
				if("join:ok".equals(checkJoin)) {
					log("채팅 서버 입장");
					break;
				} else if("join:fail".equals(checkJoin)){
					log("닉네임을 입력해주세요.");
					continue;
				}
			}

			new ChatClientThread(socket).start();
			
			while(true) {
				System.out.print(">>");
				String input = scanner.nextLine();
				
				// 메시지 공백은 클라에서 처리해보기
				if(input.isBlank()) {
					System.out.println("공백 메시지 안됨");
					continue;
				} 
				
				
				if("quit".equals(input)) {
					pw.println("quit");
					break;
				} else{
					pw.println("message:" + input);
				}
			}
		} catch (SocketException e) {
			log("error:" + e);
		} catch (IOException e) {
			log("error:" + e);
		} finally {
			try {
				if(scanner != null) {
					scanner.close();
				}
				if(socket != null && socket.isClosed() == false){
					socket.close();
				}
			}catch(IOException e) {
				log("error:" + e);
			}
		}
	}
	
	private static void log(String message) {
		System.out.println("[ChatClient]"  + message);
	}

}
