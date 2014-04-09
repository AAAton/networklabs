package lab4.monothread;

import java.util.HashMap;
import java.net.MalformedURLException;
import java.net.URL;

import lab4.exampleFiles.LinkGetter;

public class MonoThread {
	public static final String BASE_URL = "http://cs.lth.se/pierre_nugues/";

	public static void main(String[] args) {

		try {
			URL baseUrl = new URL(BASE_URL);
			LinkCollecter linkCollecter = new LinkCollecter(baseUrl);

			//HashMap<URL, URL> parentChild = linkCollecter.getLinksBreadthFirst(1000);

			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
