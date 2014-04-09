package lab4.monothread;

import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;

public class MonoThread {
	public static final String BASE_URL = "http://cs.lth.se/pierre_nugues/";

	public static void main(String[] args) {

		try {
			URL baseUrl = new URL(BASE_URL);
			LinkCollecter linkCollecter = new LinkCollecter(baseUrl);

			linkCollecter.getLinksBreadthFirst(1000);
			ArrayList<URL> links,emails;
			links = linkCollecter.getLinks();
			emails = linkCollecter.getEmailAddresses();
			
			System.out.println(links.size());
			System.out.println(emails.size());
			
		} catch (MalformedURLException e) {
			System.out.println("Your base-url is dumb");
		}
	}

}
