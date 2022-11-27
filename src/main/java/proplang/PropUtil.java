package main.java.proplang;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.*;
import com.intellij.psi.util.PsiTreeUtil;
import main.java.ctlang.CTFileType;
import main.java.ctlang.psi.CTFile;
import main.java.ctlang.psi.CTProperty;
import main.java.proplang.psi.PropProp;
import main.java.proplang.psi.PropFile;


import java.util.*;

public class PropUtil {

    // Searches the entire project for CT language files with instances of the CT property with the given key
    public static List<CTProperty> findProperties(Project project, String key, PsiFile file) {
        List<CTProperty> result = new ArrayList<>();
        String fileName = file.getVirtualFile().getNameWithoutExtension();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(CTFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            CTFile ctFile = (CTFile) PsiManager.getInstance(project).findFile(virtualFile);
                CTProperty[] properties = PsiTreeUtil.getChildrenOfType(ctFile, CTProperty.class);
                if (properties != null) {
                    for (CTProperty property : properties) {
                        if (fileName.equals(property.getKey()) && property.getValue().contains(key)) {
                            result.add(property);
                        }
                    }
                }
        }
        return result;
    }

    public static List<CTProperty> findProperties(Project project, PsiFile file) {
        List<CTProperty> result = new ArrayList<>();
        if(file.getVirtualFile() == null){
            return result;
        }
        String fileName = file.getVirtualFile().getNameWithoutExtension();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(CTFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            CTFile ctFile = (CTFile) PsiManager.getInstance(project).findFile(virtualFile);
            CTProperty[] properties = PsiTreeUtil.getChildrenOfType(ctFile, CTProperty.class);
            if (properties != null) {
                        Collections.addAll(result, properties);
            }
        }
        return result;
    }
}