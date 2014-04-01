package lab2.part1;

import java.net.URL;

import lab2.common.Download;
import lab2.part2.Part2Main;

public class RunnerThread extends Thread {

	public void run() {
		URL url;
		while ((url = Part1Main.nextLink()) != null) {
			Download.download(Part1Main.getFilename(url), url);
		}
	}
}
