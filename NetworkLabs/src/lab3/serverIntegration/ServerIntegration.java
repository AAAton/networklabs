package lab3.serverIntegration;

public class ServerIntegration {
	public static PostCentralSuperOffice mailBox;
	public static void main(String[] args) {
		mailBox = new PostCentralSuperOffice();
		new ChatServer();
	}

}
