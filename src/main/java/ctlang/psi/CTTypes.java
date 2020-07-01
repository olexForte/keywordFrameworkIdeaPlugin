// This is a generated file. Not intended for manual editing.
package ctlang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import ctlang.psi.impl.*;

public interface CTTypes {

  IElementType COMMAND = new CTElementType("COMMAND");
  IElementType COMMAND_PART = new CTElementType("COMMAND_PART");
  IElementType PROPERTY = new CTElementType("PROPERTY");
  IElementType TAG = new CTElementType("TAG");
  IElementType TAGS = new CTElementType("TAGS");

  IElementType COMMENT = new CTTokenType("COMMENT");
  IElementType CRLF = new CTTokenType("CRLF");
  IElementType KEY = new CTTokenType("KEY");
  IElementType PARAMETER_SEPARATOR = new CTTokenType("PARAMETER_SEPARATOR");
  IElementType PROPERTY_SEPARATOR = new CTTokenType("PROPERTY_SEPARATOR");
  IElementType SEPARATOR = new CTTokenType("SEPARATOR");
  IElementType STRING = new CTTokenType("STRING");
  IElementType TAGSLABEL = new CTTokenType("TAGSLABEL");
  IElementType TAG_SEPARATOR = new CTTokenType("TAG_SEPARATOR");
  IElementType VALUE = new CTTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == COMMAND) {
        return new CTCommandImpl(node);
      }
      else if (type == COMMAND_PART) {
        return new CTCommandPartImpl(node);
      }
      else if (type == PROPERTY) {
        return new CTPropertyImpl(node);
      }
      else if (type == TAG) {
        return new CTTagImpl(node);
      }
      else if (type == TAGS) {
        return new CTTagsImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
