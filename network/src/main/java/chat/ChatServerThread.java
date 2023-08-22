package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class ChatServerThread extends Thread {
	private List<PrintWriter> listPrintWriterList = new ArrayList<PrintWriter>();
	private String nickName;
	private Socket socket;
	
	public ChatServerThread(Socket socket, List<PrintWriter> listPrintWriterList) {
		this.socket = socket;
		this.listPrintWriterList = listPrintWriterList;
	}

	@Override
	public void run() {
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			while(true) {
				String data = br.readLine();
				if(data == null) {
					log("closed by client");
					doQuit(pw);
					break;
				}
				
				String[] tokens = data.split(":");
				 if("join".equals(tokens[0])) {
					 boolean checkedBlank = checkedBlank(tokens);
					 if(checkedBlank) {
						 doJoin(tokens[1], pw);
						 continue;
					 } 
					 
					pw.println("join:fail");
					pw.flush();
					
				 } else if("message".equals(tokens[0])) {
					 doMessage(tokens[1]);
					 
				 } else if("quit".equals(tokens[0])) {
					 doQuit(pw);
					 break;
				 } else {
					 log("에러:알 수 없는 요청("+tokens[0]+")");
				 }
			}
		} catch (UnsupportedEncodingException e) {
			log("error:"+e);
		} catch (IOException e) {
			log("error:"+e);
		}
	}
	
	private boolean checkedBlank(String[] tokens) {
		if(tokens.length <= 1 || tokens[1].isBlank()) {
			return false;
		}
		return true;
	}

	private void doQuit(PrintWriter pw) {
		removeWriter(pw);
		String data = nickName + "님이 퇴장하였습니다.";
		broadcast(data);
	}
	
	private void removeWriter(PrintWriter pw) {
		synchronized (listPrintWriterList) {
			listPrintWriterList.remove(pw);
		}
	}

	private void doMessage(String message) {
		String data = nickName + " : " + message;
		broadcast(data);
	}

	private void doJoin(String nickName, PrintWriter pw) {
		this.nickName = nickName;
		
		String data = nickName + "님이 참여하셨습니다.";
		broadcast(data);
		
		addWriter(pw);
		
		pw.println("join:ok");
		pw.flush();
	}

	private void addWriter(PrintWriter pw) {
		synchronized (pw) {
			listPrintWriterList.add(pw);
		}
		
	}

	private void log(String message) {
		System.out.println("[ChatServer] : " + message);
	}
	
	private void broadcast(String data) {
		synchronized (listPrintWriterList) {
			for(Writer writer : listPrintWriterList) {
				PrintWriter pw = (PrintWriter)writer;
				pw.println(data);
				pw.flush();
			}
			
		}
	}

}
