// This is a generated file. Not intended for manual editing.
package scriptslang.psi.impl;

import java.util.List;

import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static scriptslang.psi.SCTTypes.*;

import scriptslang.SCTNamedElementImpl;
import scriptslang.psi.*;

public class SCTScriptfileImpl extends SCTNamedElementImpl implements SCTScriptfile {

  public SCTScriptfileImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SCTVisitor visitor) {
    visitor.visitScriptfile(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SCTVisitor) accept((SCTVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public PsiReference[] getReferences() {
    return ReferenceProvidersRegistry.getInstance().getReferencesFromProviders(this);
  }

  @Override
  public String getValue() {
    return SCTPsiImplUtil.getValue(this);
  }

  @Override
  public String getName() {
    return SCTPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return SCTPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return SCTPsiImplUtil.getNameIdentifier(this);
  }

}
