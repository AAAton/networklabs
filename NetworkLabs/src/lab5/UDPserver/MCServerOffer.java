package lab5.UDPserver;

import java.net.*;
import java.io.*;

public class MCServerOffer implements Runnable {
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
		DatagramPacket send = new DatagramPacket(sendByte,sendByte.length,returnAddress,returnPort);
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
				offer(dp);
			}
			
		} catch(IOException e) {
			System.out.println("Exception:"+e);
		}
	}

}