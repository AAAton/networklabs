package lab3.serverIntegration;

public class Message {
	private String message;
	private long timeStamp;
	public Message(String message){
		this.message = message;
		this.timeStamp = System.currentTimeMillis();
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Message){
			Message m = (Message) obj;
			return (message.equals(m.message) && timeStamp == m.timeStamp);
		}
		return false;
	}
	public String toString(){
		return message;
	}
}
