// This is a generated file. Not intended for manual editing.
package proplang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import proplang.psi.impl.*;

public interface PropTypes {

  IElementType PROP = new PropElementType("PROP");

  IElementType COMMENT = new PropTokenType("COMMENT");
  IElementType CRLF = new PropTokenType("CRLF");
  IElementType KEY = new PropTokenType("KEY");
  IElementType SEPARATOR = new PropTokenType("SEPARATOR");
  IElementType VALUE = new PropTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROP) {
        return new PropPropImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
