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
	private static ArrayList<LinkCrawler> threads;
	public static final int MAX_LINKS = 500;

	public LinkCollecter(URL url){
		this.baseUrl = url;
		queue = new ArrayList<URL>();
		urls = new ArrayList<URL>();
		emails = new ArrayList<URL>();
		urls.add(baseUrl);
	}

	public void getLinksBreadthFirst(int maximum) {
		threads = new ArrayList<LinkCrawler>();
		LinkCrawler threadas = new LinkCrawler(baseUrl);
		threads.add(threadas);
		threadas.start();
	}

	public ArrayList<URL> getEmailAddresses(){
		return emails;
	}
	public ArrayList<URL> getLinks(){
		return urls;
	}

	private static URL getFromHeadOfQueue() {
		if(queue.isEmpty()) return null;
		URL url = queue.get(0);
		queue.remove(0);
		return url;
	}
	
	public static synchronized void callBack(LinkCrawler finishedThread){
		threads.remove(finishedThread);
		while(threads.size()< 10 && urls.size()<MAX_LINKS){
			URL u = getFromHeadOfQueue();
			if(u==null) break;
			LinkCrawler uThread = new LinkCrawler(u);
			uThread.start();
			threads.add(uThread);
		}
		
		if(threads.size()==0){
			System.out.println("FINISHED! Number of fetched urls_is "+urls.size()+", and we also found "+emails.size()+" emails.");
		}
	}
	
	public static synchronized void remove(URL url){
		urls.remove(url);
	}


	public static synchronized void addLinkToQueue(URL url,URL parent){	
		if(urls.size()>=MAX_LINKS) return;
		if(!queue.contains(url) && !urls.contains(url)){ //Have we been here before?
			queue.add(url);
			urls.add(url);
			System.out.println("\t"+url+" was added");
		}
	}

	public static synchronized void addMailAddress(String href) throws MalformedURLException {
		if(urls.size()>MAX_LINKS) return;
		URL url = new URL(href);
		if(!emails.contains(url))
			emails.add(url);
		System.out.println("\t"+href+" was added to emails");
	}
	
}
