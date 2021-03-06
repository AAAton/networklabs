package lab3.serverIntegration;

import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer {
	public ChatServer(){
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(30000);
			System.out.println("Created ChatServer");
			while (true) {
				Socket socket = serverSocket.accept();

				ClientHandler cH = new ClientHandler(socket);
				cH.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
