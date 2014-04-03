package lab3.part2;

public class MailReader extends Thread {

	public void run() {
		while (true) {
			
			
			try {
				String msg = MeatEater.mailBox.getMessages();
				if (msg != null)
					System.out.println(msg);
				Thread.sleep((long) Math.random()*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
