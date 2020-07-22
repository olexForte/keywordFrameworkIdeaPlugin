package ctlang;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CTCommandReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private final String key;

    public CTCommandReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<PsiLiteralExpression> properties = CTUtil.findCommands(project, key);
        List<ResolveResult> results = new ArrayList<>();
        for (PsiLiteralExpression property : properties) {
            results.add(new PsiElementResolveResult(property));
        }
        final List<PsiFile> actions = CTUtil.findActionFiles(project, key.trim().replace(" ", "_"));
        for (PsiFile action : actions) {
            results.add(new PsiElementResolveResult(action));
        }
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
        List<PsiLiteralExpression> commands = CTUtil.findCommands(project);
        List<LookupElement> variants = new ArrayList<>();
        for (final PsiElement command : commands) {
            if (command.getText() != null && command.getText().length() > 0) {
                PsiAnnotation l = (PsiAnnotation) command.getParent().getFirstChild().getFirstChild();
                String val = l.findAttributeValue("value").getText();
                variants.add(LookupElementBuilder
                        .create(val.substring(1, val.length()-1)).withIcon(CTIcons.FILE)
                        .withTypeText(command.getContainingFile().getName()).withIcon(CTIcons.FILE).withCaseSensitivity(false)
                );
            }
        }

        List<PsiFile> items = CTUtil.findActionFiles(project);
        for (final PsiFile item : items) {
                variants.add(LookupElementBuilder
                        .create(item.getVirtualFile().getNameWithoutExtension().replace("_", " ")).withIcon(CTIcons.FILE).withCaseSensitivity(false)
                );
        }

        return variants.toArray();
    }
}
