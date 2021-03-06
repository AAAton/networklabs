package lab5.UDPclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Locale;

public class MainClient {

	public MainClient(int port, String command) {
		Locale locale;
		locale = Locale.getDefault();

		try {
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("wut?");
			byte[] sendData = new byte[5];
			byte[] receiveData = new byte[64];
			sendData = (command +" "+ locale.toString()).getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, port);

			clientSocket.send(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println("FROM SERVER:" + modifiedSentence);
			clientSocket.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		String command = args[1];
		new MainClient(port,command);
	}

}
