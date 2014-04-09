package lab4.monothread;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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

		while(urls.size()<maximum){
			URL url = getFromHeadOfQueue();
			if(url==null) break;

			//TODO: Check mime-type!
			findLinks(url);
		}
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

	private void findLinks(URL parent) {

		System.out.println("Fetching links from "+parent);
		ParserGetter kit = new ParserGetter();
		HTMLEditorKit.Parser parser = kit.getParser();
		HTMLEditorKit.ParserCallback callback = new LinkGetterModified(parent);

		try {
			HttpURLConnection conn = (HttpURLConnection) parent.openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			if(isValidMIMEType(conn.getContentType())){
				InputStream in = new BufferedInputStream(parent.openStream());
				InputStreamReader r = new InputStreamReader(in);
				parser.parse(r, callback, true);
			}
		} catch(java.io.FileNotFoundException e){
			System.out.println("\t404 on "+parent+", removing from list...");
			urls.remove(parent);
		}catch (IOException ex) {
			ex.printStackTrace();
			System.err.println(ex);
		}
	}

	private boolean isValidMIMEType(String contentType) {
		return contentType.contains("text/html");
	}

	public static void addLinkToQueue(URL url,URL parent){	
		if(!queue.contains(url) && !urls.contains(url)){ //Have we been here before?
			queue.add(url);
			urls.add(url);
			System.out.println("\t"+url+" was added");
		}
	}

	public static void addMailAddress(String href) throws MalformedURLException {
		URL url = new URL(href);
		if(!emails.contains(url))
			emails.add(url);
		System.out.println("\t"+href+" was added to emails");
	}
}
