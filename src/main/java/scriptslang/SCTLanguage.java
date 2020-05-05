package scriptslang;

import com.intellij.lang.Language;

public class SCTLanguage extends Language {
    public static final SCTLanguage INSTANCE = new SCTLanguage();

    private SCTLanguage() {
        super("SCT");
    }
}
