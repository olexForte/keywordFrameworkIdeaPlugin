package proplang;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import ctlang.psi.CTProperty;
import proplang.psi.PropProp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PropReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private final String key;

    public PropReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<CTProperty> properties = PropUtil.findProperties(project, key, myElement.getContainingFile());
        List<ResolveResult> results = new ArrayList<>();
        for (CTProperty property : properties) {
            results.add(new PsiElementResolveResult(property));
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
    public Object[] getVariants() { //TODO
        Project project = myElement.getProject();
        List<CTProperty> properties = PropUtil.findProperties(project, myElement.getContainingFile());
        List<LookupElement> variants = new ArrayList<>();
        for (final CTProperty property : properties) {
            if (property.getKey() != null && property.getKey().length() > 0) {
                variants.add(LookupElementBuilder
                        .create(property).withIcon(PropIcons.FILE)
                        .withTypeText(property.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}
