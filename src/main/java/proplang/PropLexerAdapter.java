package proplang;
import com.intellij.lexer.FlexAdapter;

import java.io.Reader;
public class PropLexerAdapter extends FlexAdapter {
    public PropLexerAdapter() {
        super(new PropLexer((Reader) null));
    }
}