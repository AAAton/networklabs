package lab5.UDPserver;


public class MainServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int portnumber = Integer.parseInt(args[0]);
		try {
			new DateTimeServer2(portnumber);
		} catch (Throwable e) {		
			e.printStackTrace();
		}

}
}
