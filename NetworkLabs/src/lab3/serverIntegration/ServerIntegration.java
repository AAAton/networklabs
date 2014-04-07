package lab3.serverIntegration;

public class ServerIntegration {
	public static MailBox mailBox;
	public static void main(String[] args) {
		mailBox = new MailBox();
		new ChatServer();
	}

}
