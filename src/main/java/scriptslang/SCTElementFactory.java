package main.java.scriptslang;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import main.java.scriptslang.psi.SCTFile;
import main.java.scriptslang.psi.SCTScriptfile;


public class SCTElementFactory {
    public static SCTScriptfile createProperty(Project project, String name) {
        final SCTFile file = createFile(project, name);
        return (SCTScriptfile) file.getFirstChild();
    }

    public static SCTScriptfile createProperty(Project project, String name, String value) {
        final SCTFile file = createFile(project, name + " = " + value);
        return (SCTScriptfile) file.getFirstChild();
    }

    public static SCTFile createFile(Project project, String text) {
        String name = "dummy.sct";
        return (SCTFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, SCTFileType.INSTANCE, text);
    }

    public static PsiElement createCRLF(Project project) {
        final SCTFile file = createFile(project, "\n");
        return file.getFirstChild();
    }
}
