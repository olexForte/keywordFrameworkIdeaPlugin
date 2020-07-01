package ctlang;

import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.java.stubs.index.JavaStubIndexKeys;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.StubIndexImpl;
import com.intellij.psi.util.PsiTreeUtil;
import proplang.PropFileType;
import proplang.psi.PropProp;
import proplang.psi.impl.PropPropImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CTUtil {

    public static final String GLOBAL_FILE_NAME = "GLOBAL";
    public static final String RANDOM_FILE_NAME = "RANDOM";
    public static final String SAVED_FILE_NAME = "SAVED";

    public static List<PropProp> findProperties(Project project, PsiElement element){
        List<PropProp> result = new ArrayList<>();
        String key = element.getText();
        if(key.contains(".")){
            return findProperties(project, key);
        }
        result.add(new PropPropImpl(element.getNode()));
        return result;
    }

    // Searches the entire project for CT language files with instances of the CT property with the given key
    public static List<PropProp> findProperties(Project project, String key) {
        List<PropProp> result = new ArrayList<>();

        String[] parts = key.replace(":", "").toLowerCase().split("\\.");
        String filename = parts[0];
        String propname = "";
        if(parts.length > 1) {
            propname = parts[1];
        }

        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(PropFileType.INSTANCE, GlobalSearchScope.projectScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            if(filename.toLowerCase().equals(virtualFile.getNameWithoutExtension().toLowerCase())) { // if file was found
                PsiFile ctFile = PsiManager.getInstance(project).findFile(virtualFile);
                    PropProp[] properties = PsiTreeUtil.getChildrenOfType(ctFile, PropProp.class);
                    if (properties != null) {
                        for (PropProp property : properties) {
                            if(propname.equals("")){
                                result.add(property);
                            }
                            if (propname.equals(property.getKey().toLowerCase())) {
                                result.add(property);
                            }
                        }
                    }
            }
        }
        return result;
    }

    public static List<PropProp> findPropertiesForKey(Project project, PsiElement key) {
        List<PropProp> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(PropFileType.INSTANCE, GlobalSearchScope.projectScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiFile ctFile = PsiManager.getInstance(project).findFile(virtualFile);
            if (virtualFile.getNameWithoutExtension().toLowerCase().equals(key.getText().toLowerCase())) {
                PropProp[] properties = PsiTreeUtil.getChildrenOfType(ctFile, PropProp.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }

    public static List<PropProp> findProperties(Project project) {
        List<PropProp> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(PropFileType.INSTANCE, GlobalSearchScope.projectScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiFile ctFile = PsiManager.getInstance(project).findFile(virtualFile);
            if (ctFile != null) {
                PropProp[] properties = PsiTreeUtil.getChildrenOfType(ctFile, PropProp.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }


    public static List<PsiFile> findPropertiesKeys(Project project, String key) {
        List<PsiFile> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(PropFileType.INSTANCE, GlobalSearchScope.projectScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiFile ctFile = PsiManager.getInstance(project).findFile(virtualFile);
            if (key.toLowerCase().equals(virtualFile.getNameWithoutExtension().toLowerCase())) {
                  result.add(ctFile);
            }
        }
        return result;
    }

    public static List<PsiFile> findPropertiesKeys(Project project) {
        List<PsiFile> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(PropFileType.INSTANCE, GlobalSearchScope.projectScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiFile ctFile = PsiManager.getInstance(project).findFile(virtualFile);
            boolean wasFound = false;
            for(PsiFile item : result)
                if(item.getVirtualFile().getNameWithoutExtension().toLowerCase().equals(virtualFile.getNameWithoutExtension().toLowerCase())) {
                    wasFound = true;
                    break;
                }
            if(!wasFound)
                result.add(ctFile);
        }
        return result;
    }




    // Searches the entire project for CT language files with instances of the CT property with the given key
    public static List<PsiLiteralExpression> findCommands(Project project, String key) {

        ArrayList<PsiLiteralExpression> results = new ArrayList<>();
        Collection<PsiAnnotation> psiAnnotations = StubIndexImpl.getInstance().get(JavaStubIndexKeys.ANNOTATIONS, "KeywordRegexp", project, GlobalSearchScope.projectScope(project));
        for(PsiAnnotation element : psiAnnotations) {
            // get PsiLiteralExpression
            for(PsiElement child :  element.getParent().getParent().getChildren()){
                if (child instanceof PsiLiteralExpression){
                    if(child.getText().toLowerCase().trim().contains(key.toLowerCase().trim()))
                        results.add((PsiLiteralExpression)child);
                    break;
                }
            }
        }
        return results;
    }

    public static List<PsiLiteralExpression> findCommands(Project project) {
        ArrayList<PsiLiteralExpression> results = new ArrayList<>();
        Collection<PsiAnnotation> psiAnnotations = StubIndexImpl.getInstance().get(JavaStubIndexKeys.ANNOTATIONS, "KeywordRegexp", project, GlobalSearchScope.projectScope(project));
        for(PsiAnnotation element : psiAnnotations) {
            // get PsiLiteralExpression
            for(PsiElement child :  element.getParent().getParent().getChildren()){
                if (child instanceof PsiLiteralExpression){
                    results.add((PsiLiteralExpression)child);
                    break;
                }
            }
        }
        return results;
    }

    public static List<String> findTags(Project project, String possibleProperties) {
        ArrayList<String> results = new ArrayList<>();

        Collection<VirtualFile> virtualFiles =
                FilenameIndex.getVirtualFilesByName(    project,    "project.tags",    false,    GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            String content = "";
            FileInputStream fi;
            if (virtualFile != null) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(
                        (virtualFile.getInputStream()), StandardCharsets.UTF_8));) {

                    String line;
                    while ((line = br.readLine()) != null) {
                        if(possibleProperties.toLowerCase().equals(line.toLowerCase()))
                            results.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }

    public static List<String> findTags(Project project) {
        ArrayList<String> results = new ArrayList<>();

        Collection<VirtualFile> virtualFiles =
                FilenameIndex.getVirtualFilesByName(    project,    "project.tags",    false,    GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            String content = "";
            FileInputStream fi;
            if (virtualFile != null) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(
                        (virtualFile.getInputStream()), StandardCharsets.UTF_8));) {

                    String line;
                    while ((line = br.readLine()) != null) {
                        results.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }
}