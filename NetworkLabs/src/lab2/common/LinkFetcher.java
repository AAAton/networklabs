package lab2.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LinkFetcher {
	private ArrayList<URL> linkList;
	private String remoteLink,base;

	public LinkFetcher(String link, String base){
		linkList = new ArrayList<URL>();
		this.base = base;
		this.remoteLink = link;
	}

	public ArrayList<URL> giefMeLinks(){
		base = "http://www.eit.lth.se/";
		URL u;
		try {
			u = new URL(remoteLink);

			BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));

			String line;
			while((line = br.readLine()) != null){
				findLinksAndAddToList(line);
			}
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linkList;
	}
	private void findLinksAndAddToList(String line) throws MalformedURLException {
		if(line.contains(".pdf")){
			int beginningOfLink = line.indexOf("href=\"");
			int endOfLink = line.indexOf("\"", beginningOfLink+6);
			String link = line.substring(beginningOfLink+6, endOfLink);
			linkList.add(new URL(base+link));
		}
	}
}
