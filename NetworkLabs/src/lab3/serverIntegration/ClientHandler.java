package lab3.serverIntegration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;


/*
 * This class is made with MVC - pattern. Super sexy but probs not correct according to the assignment
 */
public class ClientHandler extends Thread{
//	private Socket socket;
	private OutputStream outputStream;
	private InputStream inputStream;
	private ArrayList<Message> readMessages;

	public ClientHandler(Socket socket) throws IOException{
//		this.socket=socket;
		
		ServerIntegration.mailBox.addClientHandler(this);
		
		outputStream = socket.getOutputStream();
		inputStream = socket.getInputStream();
		
		message("Welcome, bitch");
		
		readMessages = new ArrayList<Message>();
		System.out.println("Created Client Handler");
	}

	public void run(){
		try{
			while(true){
				Message clientSent = new Message(readNextMessage()); //Does this need to be separately threaded?
				readMessages.add(clientSent);
				ServerIntegration.mailBox.addMessage(clientSent);
			}

		} catch(IOException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

	public void message(String msg) throws IOException {
		outputStream.write((msg).getBytes());
		char n = '\n';
		outputStream.write((int) n);
	}

	public String readNextMessage() throws IOException{
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
		
		return sb.toString();
	}

//	public void fetchMessages(){
//		ArrayList<Message> messageList = ServerIntegration.mailBox.getMessages();
//		try {
//			for(Message message:messageList){
//				if(!readMessages.contains(message)){
//					readMessages.add(message);
//					message(message.toString());
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public void listenToMe(Message msg) {
		if(!readMessages.contains(msg)){
			try {
				message(msg.toString());
				readMessages.add(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
