package scriptslang;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class SCTNamedElementImpl extends ASTWrapperPsiElement implements SCTNamedElement {
        public SCTNamedElementImpl(@NotNull ASTNode node) {
            super(node);
        }
    }
