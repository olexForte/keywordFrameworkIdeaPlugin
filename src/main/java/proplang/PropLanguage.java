package proplang;

import com.intellij.lang.Language;

public class PropLanguage extends Language {
    public static final PropLanguage INSTANCE = new PropLanguage();

    private PropLanguage() {
        super("PROP");
    }
}
