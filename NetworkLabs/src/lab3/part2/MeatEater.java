package lab3.part2;

import java.io.IOException;

public class MeatEater {
	public static MailBox mailBox;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		mailBox = new MailBox();
		
		try {
			for (int i = 0; i < 10; i++) {
				NameWriter nw = new NameWriter("Name" + i);
				nw.start();
			}
			MailReader mailReader = new MailReader();
			mailReader.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
