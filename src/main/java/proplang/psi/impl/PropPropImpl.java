// This is a generated file. Not intended for manual editing.
package proplang.psi.impl;

import java.util.List;

import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static proplang.psi.PropTypes.*;

import proplang.PropNamedElementImpl;
import proplang.psi.*;

public class PropPropImpl extends PropNamedElementImpl implements PropProp {

  public PropPropImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PropVisitor visitor) {
    visitor.visitProp(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PropVisitor) accept((PropVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public String getKey() {
    return PropPsiImplUtil.getKey(this);
  }

  @Override
  public String getValue() {
    return PropPsiImplUtil.getValue(this);
  }

  @Override
  public String getName() {
    return PropPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return PropPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return PropPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public PsiReference[] getReferences() {
    return ReferenceProvidersRegistry.getInstance().getReferencesFromProviders(this);
  }
}
