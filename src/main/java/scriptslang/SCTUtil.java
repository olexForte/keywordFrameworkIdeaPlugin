package scriptslang;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;

import ctlang.CTFileType;
import ctlang.psi.CTFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SCTUtil {

    public static List<PsiFile> findStoryFiles(Project project, String key) {
        List<PsiFile> result = new ArrayList<>();

        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(CTFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiFile ctFile = (PsiFile) PsiManager.getInstance(project).findFile(virtualFile);
            if(virtualFile.getNameWithoutExtension().equals(key))
                result.add(ctFile);
        }
        return result;
    }

    public static List<PsiFile> findStoryFiles(Project project) {
        List<PsiFile> result = new ArrayList<>();

        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(CTFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiFile ctFile = (PsiFile) PsiManager.getInstance(project).findFile(virtualFile);
            result.add(ctFile);
        }
        return result;
    }

    public static List<String> findStories(Project project, String key) {
        List<String> result = new ArrayList<>();

        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(CTFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            if(virtualFile.getNameWithoutExtension().equals(key))
                result.add(virtualFile.getNameWithoutExtension());
        }
        return result;
    }

    public static List<String> findStories(Project project) {

        List<String> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(CTFileType.INSTANCE, GlobalSearchScope.allScope(project));
//        for (VirtualFile virtualFile : virtualFiles) {
//            CTFile ctFile = (CTFile) PsiManager.getInstance(project).findFile(virtualFile);
//            if (ctFile != null) {
//                SCTScriptfile[] properties = PsiTreeUtil.getChildrenOfType(ctFile, SCTScriptfile.class);
//                if (properties != null) {
//                    Collections.addAll(result, properties);
//                }
//            }
//        }
        for (VirtualFile virtualFile : virtualFiles) {
//            CTFile ctFile = (CTFile) PsiManager.getInstance(project).findFile(virtualFile);
//            if (ctFile != null) {
//                SCTScriptfile[] properties = PsiTreeUtil.getChildrenOfType(ctFile, SCTScriptfile.class);
//                if (properties != null) {
//                    Collections.addAll(result, properties);
//                }
//            }
            result.add(virtualFile.getNameWithoutExtension());
        }
        return result;
    }
}