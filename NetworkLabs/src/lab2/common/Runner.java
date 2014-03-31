package lab2.common;

import java.net.URL;

public class Runner {
	private URL url;
	private String filename;
	
	public Runner(URL url,String filename){
		this.url = url;
		this.filename = filename;
	}
	
	public void run(){
		Download.download(filename, url);
	}
}
