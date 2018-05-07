package com.jetbrains.rest.psi;

import javax.annotation.Nonnull;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

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
