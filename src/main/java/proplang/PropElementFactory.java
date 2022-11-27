package main.java.proplang;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import main.java.proplang.psi.PropFile;
import main.java.proplang.psi.PropProp;

public class PropElementFactory {
    public static PropProp createProperty(Project project, String name) {
        final PropFile file = createFile(project, name);
        return (PropProp) file.getFirstChild();
    }

    public static PropProp createProperty(Project project, String name, String value) {
        final PropFile file = createFile(project, name + " = " + value);
        return (PropProp) file.getFirstChild();
    }

    public static PropFile createFile(Project project, String text) {
        String name = "dummy.ct";
        return (PropFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, PropFileType.INSTANCE, text);
    }

    public static PsiElement createCRLF(Project project) {
        final PropFile file = createFile(project, "\n");
        return file.getFirstChild();
    }
}
