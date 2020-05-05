package proplang;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class PropNamedElementImpl extends ASTWrapperPsiElement implements PropNamedElement {
        public PropNamedElementImpl(@NotNull ASTNode node) {
            super(node);
        }
    }
