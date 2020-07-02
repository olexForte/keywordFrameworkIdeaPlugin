package scriptslang;


import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SCTFileType extends LanguageFileType {
    public static final SCTFileType INSTANCE = new SCTFileType();

    private SCTFileType() {
        super(SCTLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "CT Suite File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "CT Suite file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "sct";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return SCTIcons.FILE;
    }
}