package lab3.serverIntegration;

import java.util.ArrayList;

public class PostCentralSuperOffice {
//	private ArrayList<Message> messageList;
	private Message message;
	private ArrayList<ClientHandler> clientHandlers;
	
	
	public PostCentralSuperOffice(){
//		messageList = new ArrayList<Message>();
		clientHandlers = new ArrayList<ClientHandler>();
	}
	
	public synchronized void addMessage(Message msg) throws InterruptedException{
		while(message!=null) wait();
		message = msg;
		for(ClientHandler c:clientHandlers){
			c.sendToInbox(msg);
		}
		message = null;
		notifyAll();
	}
	
	
	public void addClientHandler(ClientHandler ch){
		clientHandlers.add(ch);
	}
	public void removeClientHandler(ClientHandler ch){
		clientHandlers.remove(ch);
	}
}
