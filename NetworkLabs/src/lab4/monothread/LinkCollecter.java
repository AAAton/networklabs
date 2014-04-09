package lab4.monothread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.SynchronousQueue;

public class LinkCollecter {
	private URL baseUrl;
	private HashMap<URL,URL> parentChild;
	private SynchronousQueue<URL> queue;
	
	public LinkCollecter(URL url){
		this.baseUrl = url;
	}

	public HashMap<URL, URL> getLinksBreadthFirst(int maximum) {
		parentChild = new HashMap<URL,URL>();
		
		addLinksToQueue(baseUrl);
		
		for(int i=queue.size();i<maximum;i++){
			URL url = queue.poll();
			if(url==null) break;
			addLinksToQueue(url);
		}
		
		return parentChild;
	}

	private void addLinksToQueue(URL parent) {
		//TODO: Use the java built in html-parser
		
//		try {
//
//			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//
//			String line;
//			
//			while((line = br.readLine()) != null){ //Reading line by line
//				findLinksAndAddToQueue(line);
//			}
//			
//			br.close();
//			
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
	}

	private void findLinksAndAddToQueue(String line) {
		String beginningOfLink = "href=\"";
		int lastLink = 0;
		
		//Presuming that the links start with href=" and ends with ", but no case for single apostrophe
		while(line.indexOf(beginningOfLink,lastLink)>-1){
			int startIndex = line.indexOf(beginningOfLink,lastLink)+6;
			int endIndex = line.indexOf("\"",startIndex);

			String link = line.substring(startIndex,endIndex);
			
		}
		
	}

}
