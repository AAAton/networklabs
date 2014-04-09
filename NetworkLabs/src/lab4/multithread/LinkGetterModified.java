package lab4.multithread;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class LinkGetterModified extends HTMLEditorKit.ParserCallback {

	private URL baseURL;

	public LinkGetterModified(URL baseURL) {
		super();
		this.baseURL = baseURL;
	}

	@Override
	public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int position) {

		if (tag == HTML.Tag.A) {
			String href = (String) attributes.getAttribute(HTML.Attribute.HREF);
			if(href==null) return;

			try {
				if(href.contains("mailto:")){
					LinkCollecter.addMailAddress(href);
				} else{
					URL absoluteLink = new URL(baseURL, href);
					LinkCollecter.addLinkToQueue(absoluteLink, baseURL);
				}
			} catch(java.net.MalformedURLException e){
				System.out.println("\tmalformed: "+href);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void handleEndTag(HTML.Tag tag, int position) {
	}


	@Override
	public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attributes, int pos) {
		if (tag == HTML.Tag.FRAME) {
			String href = (String) attributes.getAttribute(HTML.Attribute.SRC);
			if(href==null) return;
			try {
				URL path = new URL(baseURL,href);
				LinkCollecter.addLinkToQueue(path, baseURL);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} 
	}

	@Override
	public void handleText(char[] text, int position) {
	}
}
