package lab2.common;

import java.net.URL;

import lab2.part2.Part2Main;

public class RunnerSync {
	
	public void run(){
		URL url;
		while(true){
			url = Part2Main.nextLink();
			if(url==null) break;
			Download.download(Part2Main.getFilename(url), url);
		} 
	}
	
}
