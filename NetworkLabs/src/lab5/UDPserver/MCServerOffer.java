package lab5.UDPserver;

import java.net.*;
import java.io.*;

public class MCServerOffer extends Thread {
	static DatagramSocket datagramSocket;
	private int portNumber;
	private int msPort;

	public MCServerOffer(int msPort,int portNumber){
		this.portNumber = portNumber;
		this.msPort = msPort;
	}

	private void offer(DatagramPacket dp) throws IOException {
		String hostname = InetAddress.getLocalHost().getHostName();
		InetAddress returnAddress = dp.getAddress();
		int returnPort = dp.getPort();
		byte[] sendByte = new byte[1024];
		sendByte = ("Yo! Sexxi girls in ur neighbourhood ;););) chat now! "+hostname).getBytes();
		System.out.println("return address "+returnAddress+", return port "+returnPort);
		DatagramPacket send = new DatagramPacket(sendByte,sendByte.length,returnAddress,returnPort);
		System.out.println("Sending response");
		datagramSocket.send(send);
	}

	@Override
	public void run() {
		try {
			MulticastSocket ms = new MulticastSocket(msPort);
			InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			ms.joinGroup(ia);
			datagramSocket = new DatagramSocket(portNumber);
			
			while(true) {
				byte[] buf = new byte[65536];
				DatagramPacket dp = new DatagramPacket(buf,buf.length);
				ms.receive(dp);
				String s = new String(dp.getData(),0,dp.getLength());
				System.out.println("Received: "+s);
				if(s.startsWith("searching for timeservers. forever alone")){
					System.out.println("Matches time server request");
					offer(dp);
				}
			}
			
		} catch(IOException e) {
			System.out.println("Exception:"+e);
		}
	}

}