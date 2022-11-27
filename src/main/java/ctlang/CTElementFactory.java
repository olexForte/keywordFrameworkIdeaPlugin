package main.java.ctlang;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import main.java.ctlang.psi.CTFile;
import main.java.ctlang.psi.CTProperty;


public class CTElementFactory {
    public static CTProperty createProperty(Project project, String name) {
        final CTFile file = createFile(project, name);
        return (CTProperty) file.getFirstChild();
    }

    public static CTProperty createProperty(Project project, String name, String value) {
        final CTFile file = createFile(project, name + " = " + value);
        return (CTProperty) file.getFirstChild();
    }

    public static CTFile createFile(Project project, String text) {
        String name = "dummy.ct";
        return (CTFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, CTFileType.INSTANCE, text);
    }

    public static PsiElement createCRLF(Project project) {
        final CTFile file = createFile(project, "\n");
        return file.getFirstChild();
    }
}
