package lab2.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Download {
	public static void download(String filename, URL url){
		InputStream in;
		try {
			in = url.openStream();
			FileOutputStream fos = new FileOutputStream(new File("pdf/"+filename));
			int length = -1;
			byte[] buffer = new byte[1024];

			while((length=in.read(buffer))>-1){
				fos.write(buffer,0,length);
			}
			
			fos.flush();
			fos.close();
			in.close();
			System.out.println(filename+" downloaded.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
