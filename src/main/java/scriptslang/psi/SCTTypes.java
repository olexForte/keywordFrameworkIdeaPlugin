// This is a generated file. Not intended for manual editing.
package scriptslang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import scriptslang.psi.impl.*;

public interface SCTTypes {

  IElementType SCRIPTFILE = new SCTElementType("SCRIPTFILE");

  IElementType COMMENT = new SCTTokenType("COMMENT");
  IElementType CRLF = new SCTTokenType("CRLF");
  IElementType VALUE = new SCTTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == SCRIPTFILE) {
        return new SCTScriptfileImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
