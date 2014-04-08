package lab3.chatclient;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatUpstream extends Thread {
	private OutputStream os;
	private Socket socket;

	public ChatUpstream(Socket socket) throws IOException {
		os = socket.getOutputStream();
		this.socket = socket;
	}

	public void run() {
		Scanner scan = new Scanner(System.in);
		try {
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				os.write(input.getBytes());
				os.write("\n".getBytes());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
