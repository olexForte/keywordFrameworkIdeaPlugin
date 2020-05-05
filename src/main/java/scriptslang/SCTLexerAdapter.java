package scriptslang;
import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class SCTLexerAdapter extends FlexAdapter {
    public SCTLexerAdapter() {
        super(new SCTLexer((Reader) null));
    }
}