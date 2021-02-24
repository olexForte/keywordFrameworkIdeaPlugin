package ctlang;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import ctlang.psi.impl.CTPropertyImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import proplang.PropIcons;
import proplang.psi.PropProp;

import java.util.ArrayList;
import java.util.List;

public class CTPropertyReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private final String key;

    public CTPropertyReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, (element.getText().contains(":") ? textRange.grown(-1) : textRange));
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset()).replace(":", "");
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<PropProp> properties = CTUtil.findProperties(project, key);
        List<ResolveResult> results = new ArrayList<>();
        for (PropProp property : properties) {
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
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<PropProp> properties = CTUtil.findProperties(project);
        List<LookupElement> variants = new ArrayList<>();
        for (final PropProp property : properties) {
            if (property.getKey() != null && property.getKey().length() > 0) {
                variants.add(LookupElementBuilder
                        .create(property.getContainingFile().getVirtualFile().getNameWithoutExtension() + "." + property.getKey()).withIcon(PropIcons.FILE).withCaseSensitivity(false)
                        //.withTypeText()
                );
            }
        }

        variants.add(LookupElementBuilder.create("RANDOM.label:10c"));
        variants.add(LookupElementBuilder.create("RANDOM.number:5d"));
        variants.add(LookupElementBuilder.create("RANDOM.password:10l"));
        variants.add(LookupElementBuilder.create("RANDOM.curdate:CURRENT_DATE"));

        variants.add(LookupElementBuilder.create("SAVED.value"));

        return variants.toArray();
    }
}
