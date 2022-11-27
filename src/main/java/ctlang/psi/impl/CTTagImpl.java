// This is a generated file. Not intended for manual editing.
package main.java.ctlang.psi.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static main.java.ctlang.psi.CTTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import main.java.ctlang.psi.*;

public class CTTagImpl extends ASTWrapperPsiElement implements CTTag {

  public CTTagImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CTVisitor visitor) {
    visitor.visitTag(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CTVisitor) accept((CTVisitor)visitor);
    else super.accept(visitor);
  }

//  @Override
//  public void subtreeChanged() {
//    super.subtreeChanged();
//  }

  @Override
  public PsiReference[] getReferences() {
    return ReferenceProvidersRegistry.getReferencesFromProviders(this);
  }

  @Override
  public PsiReference getReference() {
    PsiReference[] references = getReferences();
    return references.length == 0 ? null : references[0];
  }

}
