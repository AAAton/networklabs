package lab2.part1;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import lab2.common.LinkFetcher;

public class Part1Main {
	private static ArrayList<URL> linkList;
	private static int index;
	public static void main(String[] args) {
		LinkFetcher lf = new LinkFetcher("http://www.eit.lth.se/index.php?ciuid=729&coursepage=4452","http://www.eit.lth.se/");
		linkList = lf.giefMeLinks();
		
		File folder = new File("pdf");
		if(!folder.exists()) folder.mkdir();
		
		for(int i= 0;i<linkList.size();i++){
			URL url = linkList.get(i);
			RunnerThread r = new RunnerThread(url, "PDF-"+i+".pdf");
			r.start();
		}
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
