package lab4.monothread;

import java.util.ArrayList;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MonoThread {
	public static final String BASE_URL = "http://cs.lth.se/pierre_nugues/";

	public static void main(String[] args) {

		try {
			URL baseUrl = new URL(BASE_URL);
			LinkCollecter linkCollecter = new LinkCollecter(baseUrl);

			linkCollecter.getLinksBreadthFirst(100);
			ArrayList<URL> links,emails;
			links = linkCollecter.getLinks();
			emails = linkCollecter.getEmailAddresses();
			
			writeToFile(links,"links.txt");
			writeToFile(emails,"emails.txt");
		
		} catch (MalformedURLException e) {
			System.out.println("Your base-url is dumb");
		}
	}

	private static void writeToFile(ArrayList<URL> links, String filename) {
		
		File folder = new File("links");
		if(!folder.exists()) folder.mkdir();
		
		File file = new File(folder,filename);
		try {
			FileWriter fileWriter = new FileWriter(file);
			for(URL link:links){
				fileWriter.write(link.toString());
				fileWriter.write('\n');
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
