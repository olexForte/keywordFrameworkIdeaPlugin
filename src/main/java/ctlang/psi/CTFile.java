package main.java.ctlang.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import main.java.ctlang.CTFileType;
import main.java.ctlang.CTLanguage;
import org.jetbrains.annotations.NotNull;

public class CTFile extends PsiFileBase {
    public CTFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, CTLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return CTFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "CT File";
    }
}