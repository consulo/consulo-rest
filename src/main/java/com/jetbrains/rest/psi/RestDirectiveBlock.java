package com.jetbrains.rest.psi;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;

import jakarta.annotation.Nonnull;

/**
 * User : catherine
 */
public class RestDirectiveBlock extends RestElement {
  public RestDirectiveBlock(@Nonnull final ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return "RestDirective:" + getNode().getElementType().toString();
  }

  @Nonnull
  public String getDirectiveName() {
    PsiElement child = this.getFirstChild();
    if (child != null)
      return child.getText();
    else
      return "";
  }
}
