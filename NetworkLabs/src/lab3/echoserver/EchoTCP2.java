package lab3.echoserver;

import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP2 {

	@SuppressWarnings("resource")
	public EchoTCP2() {

		try {

			ServerSocket serverSocket = new ServerSocket(30000);
			while (true) {
				Socket socket = serverSocket.accept();

				ClientHandlerrr cH = new ClientHandlerrr(socket);
				cH.start();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}