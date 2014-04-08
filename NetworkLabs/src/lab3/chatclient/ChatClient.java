package lab3.chatclient;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static String machine;
	private static int port;
	public static void main(String[] args) {
		try{


			machine = args[0];
			port = Integer.parseInt(args[1]);
			Socket socket = new Socket(machine,port);
			Scanner scan = new Scanner(System.in);
			OutputStream oS = socket.getOutputStream();
			
			while(scan.hasNextLine()){
				String input = scan.nextLine();
				System.out.println(input);
				oS.write(input.getBytes());
			}

		} catch(IndexOutOfBoundsException e){
			System.out.println("You have to write both machine and port number");
		} catch(NumberFormatException e){
			System.out.println("You suck");
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

}
