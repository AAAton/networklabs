package lab5.UDPserver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeServer2 {
	Date date;
	DatagramSocket socket;

	public DateTimeServer2(int portnumber) throws Throwable {
		socket = new DatagramSocket(portnumber);
		MCServerOffer t = new MCServerOffer(4099,portnumber+1);
		t.start();
		
		while (true) {
			Client client = recieve();
			String response = getTimeDate(client.getCommand(),client.getLocale()); 
			send(client, response);
		}
	}

	public Client recieve() throws Throwable{
		byte[] rData = new byte[64];
		DatagramPacket receivePacket = new DatagramPacket(rData, rData.length);
		socket.receive(receivePacket);
		int length = receivePacket.getLength();
		System.out.println("Length of message: "+receivePacket.getLength());
		String sentence = new String( receivePacket.getData()); //"date SV_se" eller "time SV_se"
		sentence = sentence.substring(0, length);
		String[] words = sentence.split("\\s+");
		String command = words[0];
		Locale locale = new Locale(words[1]);
		System.out.println("Locale: "+locale);
		System.out.println("Command: " + command);       
		InetAddress IPAddress = receivePacket.getAddress();            
		int port = receivePacket.getPort(); 
		return new Client(IPAddress,port,locale,command);
	}

	
	

	private void send(Client client, String response) throws Throwable {
		byte[] sData = new byte[1024];
		sData = response.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sData, sData.length, client.getIp(), client.getPort());
		socket.send(sendPacket);

	}
	public String getTimeDate(String command, Locale locale){
		DateFormat dateFormat;
		if(command.equals("time")){
			dateFormat = DateFormat.getTimeInstance(0,locale);
		} else {
			dateFormat = DateFormat.getDateInstance(0,locale);
		}
		String timeString = dateFormat.format(new Date());
		return timeString;
	}
}
