package lab3.echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP1 {

	public EchoTCP1() {
		try {
			ServerSocket serverSocket = new ServerSocket(30000);

				Socket socket = serverSocket.accept();
				InetAddress inetAddress = socket.getInetAddress();
				System.out.println(inetAddress);
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
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

				byte[] output = sb.toString().getBytes();

				outputStream.write(output);
				outputStream.flush();
				outputStream.close();
				inputStream.close();
				socket.close();
				serverSocket.close();	
				
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
