package lab3.echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandlerrr extends Thread {
	Socket socket;
	OutputStream outputStream;
	InputStream inputStream;

	public ClientHandlerrr(Socket socket) {
		this.socket = socket;

	}

	public void run() {
		try {
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();
			
			message("Welcome to the server man.");
			
			String readmessage = readNextMessage();
			message(readmessage);
			
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void message(String msg) throws IOException {
		outputStream.write((msg).getBytes());
		char n = '\n';
		outputStream.write((int) n);
	}
	public String readNextMessage() throws IOException{
		int readByte;
		StringBuilder sb = new StringBuilder();
		do {
			readByte = inputStream.read();
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
