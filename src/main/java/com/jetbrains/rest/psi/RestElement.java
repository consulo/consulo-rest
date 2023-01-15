package com.jetbrains.rest.psi;

import com.jetbrains.rest.validation.RestElementVisitor;
import consulo.language.ast.ASTNode;
import consulo.language.impl.psi.ASTWrapperPsiElement;
import consulo.language.psi.NavigatablePsiElement;
import consulo.language.psi.PsiElementVisitor;

import javax.annotation.Nonnull;

public class RestElement extends ASTWrapperPsiElement implements NavigatablePsiElement {

  public RestElement(@Nonnull final ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return "RestElement:" + getNode().getElementType().toString();
  }

  protected void acceptRestVisitor(RestElementVisitor visitor) {
    visitor.visitRestElement(this);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof RestElementVisitor) {
      acceptRestVisitor(((RestElementVisitor)visitor));
    }
    else {
      super.accept(visitor);
    }
  }

}
