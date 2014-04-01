package lab2.part3;

import java.net.URL;
import java.util.concurrent.Callable;

import lab2.common.Download;
import lab2.part2.Part2Main;

public class RunnerCallable implements Runnable {
	URL url;

	public RunnerCallable(URL url) {
		this.url = url;

	}

	public void run() {

		Download.download(Part3Main.getFilename(url), url);
	}

}