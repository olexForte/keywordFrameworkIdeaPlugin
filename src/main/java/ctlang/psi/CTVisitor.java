// This is a generated file. Not intended for manual editing.
package ctlang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class CTVisitor extends PsiElementVisitor {

  public void visitCommand(@NotNull CTCommand o) {
    visitPsiElement(o);
  }

  public void visitCommandPart(@NotNull CTCommandPart o) {
    visitPsiElement(o);
  }

  public void visitProperty(@NotNull CTProperty o) {
    visitNamedElement(o);
  }

  public void visitTag(@NotNull CTTag o) {
    visitPsiElement(o);
  }

  public void visitTags(@NotNull CTTags o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull CTNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
