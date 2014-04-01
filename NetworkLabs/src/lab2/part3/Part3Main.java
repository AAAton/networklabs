package lab2.part3;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lab2.common.LinkFetcher;

public class Part3Main {
	private static ArrayList<URL> linkList;
	private static int index;
	
	public static void main(String[] args){
		LinkFetcher lf = new LinkFetcher("http://www.eit.lth.se/index.php?ciuid=729&coursepage=4452","http://www.eit.lth.se/");
		linkList = lf.giefMeLinks();

		File folder = new File("pdf");
		if(!folder.exists()) folder.mkdir();
		
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		for(URL url : linkList){
			Runnable task = new RunnerCallable(url);
			pool.submit(task);
		}
		pool.shutdown();
	}
	
	public static synchronized URL nextLink(){
		index++;
		if(index<linkList.size())
			return linkList.get(index-1);
		return null;
	}
	public static String getFilename(URL url){
		return "PDF-"+linkList.indexOf(url)+".pdf";
	}
}
