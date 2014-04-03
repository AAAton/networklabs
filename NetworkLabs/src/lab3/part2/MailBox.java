package lab3.part2;

public class MailBox {
	private String mailBox;
	
	
	public synchronized void addMessage(String msg) throws InterruptedException{
		while(mailBox!=null) wait();
		mailBox = msg;
		notifyAll();
	}
	public synchronized String getMessages() throws InterruptedException{
		while(mailBox == null)
			wait();
		
		String msg = mailBox;
		mailBox = null;
		notifyAll();
		return msg;
	}

}
