package ctlang;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import ctlang.psi.impl.CTTagImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import proplang.PropFileType;
import scriptslang.SCTUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CTTagReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private final String key;

    public CTTagReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        List<ResolveResult> results = new ArrayList<>();

        final List<PsiFile> actions= new ArrayList();
        Collection<VirtualFile> virtualFiles =
                FilenameIndex.getVirtualFilesByName(    project,    "project.tags",    false,    GlobalSearchScope.allScope(project));
       virtualFiles.stream().map(f -> PsiManager.getInstance(project).findFile(f)).map(f -> results.add(new PsiElementResolveResult(f)));
        //PsiAnnotationConstantValue l = (PsiAnnotationConstantValue)((PsiAnnotationImpl) property.getParent().getFirstChild().getFirstChild()).getAttributes().get(0).getAttributeValue();
        //l.getConstantValue()
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<String> tags = CTUtil.findTags(project);
        List<LookupElement> variants = new ArrayList<>();
        for (final String tag : tags) {
            if (tag != null && tag.length() > 0) {
                variants.add(LookupElementBuilder
                        .create(tag).withIcon(CTIcons.FILE)
                        .withTypeText("TAG").withIcon(CTIcons.FILE).withCaseSensitivity(false)
                );
            }
        }
        return variants.toArray();
    }
}
