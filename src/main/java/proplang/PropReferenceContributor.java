package proplang;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static proplang.PropAnnotator.CT_PREFIX_STR;
import static proplang.PropAnnotator.CT_SEPARATOR_STR;


public class PropReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {
                        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                        String value = literalExpression.getValue() instanceof String ?
                                (String) literalExpression.getValue() : null;
                        if ((value != null && value.startsWith(CT_PREFIX_STR + CT_SEPARATOR_STR))) {
                            TextRange property = new TextRange(CT_PREFIX_STR.length() + CT_SEPARATOR_STR.length() + 1,
                                    value.length() + 1);
                            return new PsiReference[]{new PropReference(element, property)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }
}
