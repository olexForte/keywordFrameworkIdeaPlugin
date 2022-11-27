package main.java.scriptslang;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import main.java.scriptslang.psi.SCTScriptfile;


public class SCTReferenceContributor extends PsiReferenceContributor {

    String CT_SEPARATOR_STR = "";
    String CT_PREFIX_STR = "";

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement( SCTScriptfile.class), new STCReferenceProvider());
    }
}
