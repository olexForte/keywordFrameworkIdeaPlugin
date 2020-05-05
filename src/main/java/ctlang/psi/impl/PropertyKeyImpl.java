package ctlang.psi.impl;

import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class PropertyKeyImpl extends LeafPsiElement {
    public PropertyKeyImpl(@NotNull IElementType type, CharSequence text) {
        super(type, text);
    }

    @Override
    public PsiReference[] getReferences() {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this);
    }

    @Override
    public PsiReference getReference() {
        PsiReference[] references = getReferences();
        return references.length == 0 ? null : references[0];
    }

    @Override
    @NonNls
    public String toString() {
        return "Property key: " + getText();
    }
}
