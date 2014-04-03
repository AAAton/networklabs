package lab3.part2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NameWriter extends Thread {
	String name;
	Socket socket;
	OutputStream outputStream;
	InputStream inputStream;

	public NameWriter(String name) throws IOException {
		this.name = name;
		//this.socket = socket;
//		outputStream = socket.getOutputStream();
//		inputStream = socket.getInputStream();

	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			
			try {
				MeatEater.mailBox.addMessage(name);
				Thread.sleep((long)Math.random()*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

//	public void message(String msg) throws IOException {
//		outputStream.write((msg).getBytes());
//		char n = '\n';
//		outputStream.write((int) n);
//	}

}
