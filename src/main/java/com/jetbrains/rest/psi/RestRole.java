package com.jetbrains.rest.psi;

import com.jetbrains.rest.validation.RestElementVisitor;
import consulo.language.ast.ASTNode;

import jakarta.annotation.Nonnull;

/**
 * User : catherine
 */
public class RestRole extends RestElement {
  public RestRole(@Nonnull final ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return "RestRole:" + getNode().getElementType().toString();
  }

  public String getRoleName() {
    String text = getNode().getText();
    return text.substring(1, text.length()-1);
  }

  @Override
  protected void acceptRestVisitor(RestElementVisitor visitor) {
    visitor.visitRole(this);
  }
}
