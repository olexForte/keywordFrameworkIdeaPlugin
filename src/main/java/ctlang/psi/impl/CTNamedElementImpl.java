package ctlang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import ctlang.psi.CTNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class CTNamedElementImpl extends ASTWrapperPsiElement implements CTNamedElement {
        public CTNamedElementImpl(@NotNull ASTNode node) {
            super(node);
        }
    }
