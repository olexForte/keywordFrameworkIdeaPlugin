// This is a generated file. Not intended for manual editing.
package ctlang.psi.impl;

import java.util.List;

import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ctlang.psi.CTTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import ctlang.psi.*;

public class CTCommandImpl extends ASTWrapperPsiElement implements CTCommand {

  public CTCommandImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CTVisitor visitor) {
    visitor.visitCommand(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CTVisitor) accept((CTVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public PsiReference[] getReferences() {
    return ReferenceProvidersRegistry.getReferencesFromProviders(this);
  }

  @Override
  @NotNull
  public CTCommandPart getCommandPart() {
    return findNotNullChildByClass(CTCommandPart.class);
  }

  @Override
  @NotNull
  public List<CTProperty> getPropertyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CTProperty.class);
  }

}
