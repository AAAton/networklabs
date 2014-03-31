package lab2.part1;

import java.net.URL;

import lab2.common.Download;

public class RunnerThread extends Thread{
	private URL url;
	private String filename;
	
	public RunnerThread(URL url,String filename){
		this.url = url;
		this.filename = filename;
	}
	
	public void run(){
		Download.download(filename, url);
	}
}
