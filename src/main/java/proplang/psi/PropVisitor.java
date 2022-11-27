// This is a generated file. Not intended for manual editing.
package main.java.proplang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import main.java.proplang.PropNamedElement;

public class PropVisitor extends PsiElementVisitor {

  public void visitProp(@NotNull PropProp o) {
    visitNamedElement(o);
  }

  public void visitNamedElement(@NotNull PropNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
