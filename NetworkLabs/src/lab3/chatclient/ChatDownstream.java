package lab3.chatclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ChatDownstream extends Thread {
	
	private InputStream in;
	public ChatDownstream(Socket socket) throws IOException{
		in = socket.getInputStream();
	}
	
	public void run(){
		while(true){
			try {
				String s = readNextMessage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String readNextMessage() throws IOException{
		int readByte;
		StringBuilder sb = new StringBuilder();
		do {
			readByte = in.read();
			char a = (char) readByte;
			sb.append(a);
			System.out.print(a);
			if (a == '\n') {
				readByte = -1;
			}
		} while (readByte != -1);
		
		return sb.toString();
	}
}
