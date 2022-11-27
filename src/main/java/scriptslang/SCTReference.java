package main.java.scriptslang;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import main.java.scriptslang.psi.SCTScriptfile;
import main.java.scriptslang.psi.impl.SCTScriptfileImpl;

import java.util.ArrayList;
import java.util.List;

public class SCTReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private final String key;

    public SCTReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<PsiFile> properties = SCTUtil.findStoryFiles(project, key);
        List<ResolveResult> results = new ArrayList<>();
        for (PsiFile property : properties) {
            results.add(new PsiElementResolveResult(property)); //TODO
        }
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
        List<String> properties = SCTUtil.findStories(project);
        List<LookupElement> variants = new ArrayList<>();
        for (final String property : properties) {
            if (property != null && property.length() > 0) {
                variants.add(LookupElementBuilder
                        .create(property).withIcon(SCTIcons.FILE)
                        .withTypeText(property)
                );
            }
        }
        return variants.toArray();
    }
}
