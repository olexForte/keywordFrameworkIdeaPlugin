package ctlang;


import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ACTFileType extends LanguageFileType {
    public static final ACTFileType INSTANCE = new ACTFileType();

    private ACTFileType() {
        super(CTLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Action CT File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "CT language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "act";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return CTIcons.ACTION_FILE;
    }
}