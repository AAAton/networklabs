package lab3.serverIntegration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ClientHandler extends Thread implements Observer{
	private Socket socket;
	private OutputStream outputStream;
	private InputStream inputStream;
	private ArrayList<Message> unreadMessages,readMessages;

	public ClientHandler(Socket socket) throws IOException{
		this.socket=socket;
		ServerIntegration.mailBox.addObserver(this);
		outputStream = socket.getOutputStream();
		inputStream = socket.getInputStream();
	}

	public void run(){
		try{
			while(unreadMessages.size()>0){
				Message m = unreadMessages.get(0);
				message(m.toString());
				unreadMessages.remove(0);
			}
			
			Message clientSent = new Message(readNextMessage()); //Does this need to be separately threaded?
			ServerIntegration.mailBox.addMessage(clientSent);
			//TODO add message to mailbox
			
			
		} catch(IOException e){

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

	public void getMessages(){
		ArrayList<Message> messageList = ServerIntegration.mailBox.getMessages();
		for(Message message:messageList){
			if(!readMessages.contains(message) && !unreadMessages.contains(message)){
				unreadMessages.add(message);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		getMessages();
	}
}
