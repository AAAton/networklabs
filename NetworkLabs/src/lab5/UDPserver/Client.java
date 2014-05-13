package lab5.UDPserver;

import java.net.InetAddress;
import java.util.Locale;

public class Client {

	private InetAddress ip;
	private int port;
	private Locale locale;
	private String command;

	public Client(InetAddress iPAddress, int port, Locale locale,String command) {
		this.ip = iPAddress;
		this.port = port;
		this.locale = locale;
		this.command = command;
	}

	public InetAddress getIp() {
		// TODO Auto-generated method stub
		return ip;
	}

	public int getPort() {
		// TODO Auto-generated method stub
		return port;
	}

	public Locale getLocale() {
		// TODO Auto-generated method stub
		return locale;
	}
	public String getCommand(){
		return command;
	}
}
