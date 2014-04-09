package lab4.monothread;

import javax.swing.text.html.HTMLEditorKit;

@SuppressWarnings("serial")
public class ParserGetter extends HTMLEditorKit {

    // purely to make this method public
    @Override
    public HTMLEditorKit.Parser getParser() {
        return super.getParser();
    }
}
