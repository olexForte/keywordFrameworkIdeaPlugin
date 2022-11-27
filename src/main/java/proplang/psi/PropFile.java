package main.java.proplang.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import main.java.proplang.PropFileType;
import main.java.proplang.PropLanguage;
import org.jetbrains.annotations.NotNull;

public class PropFile extends PsiFileBase {
    public PropFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, PropLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return PropFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "PROP File";
    }
}