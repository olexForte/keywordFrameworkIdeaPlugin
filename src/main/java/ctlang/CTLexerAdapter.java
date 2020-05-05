package ctlang;
import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class CTLexerAdapter extends FlexAdapter {
    public CTLexerAdapter() {
        super(new CTLexer((Reader) null));
    }
}