package ctlang;


import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CTFileType extends LanguageFileType {
    public static final CTFileType INSTANCE = new CTFileType();

    private CTFileType() {
        super(CTLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "CT File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "CT language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "ct";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return CTIcons.FILE;
    }
}