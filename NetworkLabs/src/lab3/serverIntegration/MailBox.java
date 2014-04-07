package lab3.serverIntegration;

import java.util.ArrayList;
import java.util.Observable;

public class MailBox extends Observable{
	private ArrayList<Message> messageList;
	private Message message;
	
	public synchronized void addMessage(Message msg){
		messageList.add(msg);
		this.notifyObservers();	
	}
	
	@SuppressWarnings("unchecked")
	public synchronized ArrayList<Message> getMessages(){		
		return (ArrayList<Message>) messageList.clone();
	}
}
