package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private static final String DOCUMENT_ROOT_STRING = "./webapp";
	private Socket socket;
	
	public RequestHandler( Socket socket ) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			
			InetSocketAddress inetSocketAddress = ( InetSocketAddress )socket.getRemoteSocketAddress();
			log( "connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort() );
			
			// get IOStream
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			String request = null;
			
			while(true) {
				String line = br.readLine();
				
				// 브라우저가 연결을 끊으면,
				if(line == null) {
					break;
				}
			
				// SumpleHttpServer는 요청의헤더만 처리한다.
				if("".equals(line)) {
					break;
				}
				
				// 요청 헤더의 첫번째 라인만 읽음
				if(request == null) {
					request = line;
					break;
				}
				
//				log(line);
				
				log(request);
				
				String[] tokens = request.split(" ");
				log("1111");
				if("GET".equals(tokens[0])) {
					log("2222");
					responseStaticResource(outputStream, tokens[1], tokens[2]);
				} else {
					// method:POST, PUT, DELETE, HEAD, CONNECT
					// SimpleHttpServer에서는 무시(400 Bad Request)
					System.out.println("400 Bad Request:" + request);
					responseStatic400Error(outputStream, tokens[2]);
					
				}
				
			}

			log(request);

		} catch( Exception ex ) {
			log( "error:" + ex );
		} finally {
			// clean-up
			try{
				if( socket != null && socket.isClosed() == false ) {
					socket.close();
				}
				
			} catch( IOException ex ) {
				log( "error:" + ex );
			}
		}			
	}

	private void responseStatic400Error(OutputStream outputStream, String string) {
		// HTTP/1.1 400 Bad Request\r\n
		// Content-Type:text/html; charset=utf-8\r\n
		// \r\n
		// error/400.html 내용
		
	}

	private void responseStaticResource(
			OutputStream outputStream, 
			String url, String protocol) throws IOException{
		
		System.out.println("url : "+url);
		// default(welcome) file
		if("/".equals(url)) {
			url = "/index.html";
		}
		
		File file = new File(DOCUMENT_ROOT_STRING + url);
		if(!file.exists()) {
			System.out.println("404 File Not Found:" + url);
			responseStatic404Error(outputStream, protocol);
			return;
		}
		
		// nio
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		
		// 응답
		outputStream.write("HTTP/1.1 200 OK\r\n".getBytes( "UTF-8" ) );
		outputStream.write(("Content-Type:"+ contentType +"; charset=utf-8\r\n").getBytes( "UTF-8" ));
		outputStream.write("\r\n".getBytes() );
		outputStream.write(body);
	}

	private void responseStatic404Error(OutputStream outputStream, String protocol) {
		// HTTP/1.1 404 File Not Found\r\n
		// Content-Type:text/html; charset=utf-8\r\n
		// \r\n
		// error/404.html 내용
		
	}

	public void log( String message ) {
		System.out.println( "[RequestHandler#" + getId() + "] " + message );
	}
}
