package lab4.multithread;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.text.html.HTMLEditorKit;

public class LinkCrawler extends Thread {
	private URL parent;
	
	public LinkCrawler(URL parent){
		this.parent = parent;
	}
	@Override
	public void run() {
		findLinks();
	}
	private void findLinks() {

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
			LinkCollecter.remove(parent);
		}catch (IOException ex) {
			ex.printStackTrace();
			System.err.println(ex);
		}
	}
	
	private boolean isValidMIMEType(String contentType) {
		return contentType.contains("text/html");
	}
	
}
