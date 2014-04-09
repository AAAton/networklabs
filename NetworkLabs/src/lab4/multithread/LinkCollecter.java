package lab4.multithread;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.text.html.HTMLEditorKit;

public class LinkCollecter {
	private URL baseUrl;
	private static ArrayList<URL> urls,queue,emails;

	public LinkCollecter(URL url){
		this.baseUrl = url;
		queue = new ArrayList<URL>();
		urls = new ArrayList<URL>();
		emails = new ArrayList<URL>();

	}

	public void getLinksBreadthFirst(int maximum) {		
		queue.add(baseUrl);
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		while(urls.size()<maximum){
			URL url = getFromHeadOfQueue();
			if(url==null) break;
			Thread crawler = new LinkCrawler(url);
			pool.submit(crawler);
		}
		pool.shutdown();
	}

	public ArrayList<URL> getEmailAddresses(){
		return emails;
	}
	public ArrayList<URL> getLinks(){
		return urls;
	}

	private URL getFromHeadOfQueue() {
		if(queue.isEmpty()) return null;
		URL url = queue.get(0);
		queue.remove(0);
		return url;
	}
	
	public static synchronized void remove(URL url){
		urls.remove(url);
	}


	public static synchronized void addLinkToQueue(URL url,URL parent){	
		if(!queue.contains(url) && !urls.contains(url)){ //Have we been here before?
			queue.add(url);
			urls.add(url);
			System.out.println("\t"+url+" was added");
		}
	}

	public static synchronized void addMailAddress(String href) throws MalformedURLException {
		URL url = new URL(href);
		if(!emails.contains(url))
			emails.add(url);
		System.out.println("\t"+href+" was added to emails");
	}
	
}
