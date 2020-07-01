package ctlang;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import ctlang.psi.CTCommand;
import ctlang.psi.CTCommandPart;
import ctlang.psi.CTProperty;
import ctlang.psi.CTTag;
import org.jetbrains.annotations.NotNull;
import proplang.PropReference;
import proplang.psi.PropProp;
import scriptslang.SCTLanguage;
import scriptslang.STCReferenceProvider;
import scriptslang.psi.SCTScriptfile;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class CTReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
//        registrar.registerReferenceProvider(  psiElement(SCTScriptfile.class).withLanguage(SCTLanguage.INSTANCE),
//                 new STCReferenceProvider());

        registrar.registerReferenceProvider(  psiElement(SCTScriptfile.class).withLanguage(SCTLanguage.INSTANCE),
                 new STCReferenceProvider());

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(CTCommandPart.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {
                        String value = element.getText() instanceof String ?
                                (String) element.getText() : null;
                        if (value != null) {
                            TextRange range = new TextRange(0, value.length());
                            return new PsiReference[]{new CTCommandReference(element, range)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(CTProperty.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {
                        String value = element.getText() instanceof String ?
                                (String) element.getText() : null;
                        if (value != null) {
                            TextRange range = new TextRange(0, value.length());
                            return new PsiReference[]{new CTPropertyReference(element, range)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PropProp.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {
                        String value = element.getText() instanceof String ?
                                (String) element.getText() : null;
                        if (value != null) {
                            TextRange range = new TextRange(0,
                                    value.length());
                            return new PsiReference[]{new PropReference(element, range)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(CTTag.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {
                        String value = element.getText() instanceof String ?
                                (String) element.getText() : null;
                        if (value != null) {
                            TextRange range = new TextRange(0,
                                    value.length());
                            return new PsiReference[]{new CTTagReference(element, range)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }
}
