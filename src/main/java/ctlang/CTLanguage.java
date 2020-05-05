package ctlang;

import com.intellij.lang.Language;

public class CTLanguage extends Language {
    public static final CTLanguage INSTANCE = new CTLanguage();

    private CTLanguage() {
        super("CT");
    }
}
