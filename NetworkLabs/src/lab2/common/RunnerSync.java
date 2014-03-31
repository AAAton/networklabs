package lab2.common;

import java.net.URL;

import lab2.part2.Part2Main;

public class RunnerSync {
	
	public void run(){
		URL url;
		while((url = Part2Main.nextLink())!=null){
			Download.download(Part2Main.getFilename(url), url);
		} 
	}
	
}
