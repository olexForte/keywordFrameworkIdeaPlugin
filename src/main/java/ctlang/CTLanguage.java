package ctlang;

import com.intellij.lang.Language;

public class CTLanguage extends Language {

    public static final String TAGS_FILE = "project.tags";

    public static final CTLanguage INSTANCE = new CTLanguage();

    private CTLanguage() {
        super("CT");
    }
}
