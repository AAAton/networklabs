package lab5.UDPclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Locale;

public class SnedUDP2 {
	private DatagramSocket clientSocket;
	public SnedUDP2(int port, String command) {
		Locale locale;
		locale = Locale.getDefault();

		try {
			clientSocket = new DatagramSocket(port+2);
			String hostAddress = discover();
			System.out.println("Found host at "+hostAddress);
			InetAddress IPAddress = InetAddress.getByName(hostAddress);
			System.out.println("Sending request for a sexi date to "+IPAddress);
			byte[] sendData = new byte[32];
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


	public String discover() throws IOException{

		MulticastSocket ms = new MulticastSocket();
		ms.setTimeToLive(1);
		InetAddress ia = InetAddress.getByName("experiment.mcast.net");

			String s = "searching for timeservers. forever alone";
			System.out.println("Sending message: "+s);
			byte[] buf = s.getBytes();
			DatagramPacket dp = new DatagramPacket(buf,buf.length,ia,4099);
			ms.send(dp);
			
			String hostName = receiveServerOffers(ms);
			
			return hostName;
	}
	
	private String receiveServerOffers(MulticastSocket ms) throws IOException {
		byte[] receiveData = new byte[1024];
		DatagramPacket dp = new DatagramPacket(receiveData, receiveData.length);
		System.out.println("Trying to receive messages");
		ms.receive(dp);
		String sentence = new String(dp.getData());
		System.out.println("received: \""+sentence+"\"");
		if(sentence.startsWith("Yo! Sexxi girls in ur area ;););) chat now!")){
			System.out.println("Received message and it matches command");
			String[] words = sentence.split("\\s+");
			String hostname = words[words.length-1];
			return hostname;
		}
		return null;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		String command = args[1];
		new SnedUDP2(port,command);
	}

}
