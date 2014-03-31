package lab2.part2;

import java.net.URL;
import java.util.concurrent.Callable;

import lab2.common.Download;
import lab2.part2.Part2Main;

public class RunnerSync implements Runnable {
	
	public void run(){
		URL url;
		while((url = Part2Main.nextLink())!=null){
			Download.download(Part2Main.getFilename(url), url);
		} 
	}
	
}

