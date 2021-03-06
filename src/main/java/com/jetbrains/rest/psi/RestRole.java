package com.jetbrains.rest.psi;

import javax.annotation.Nonnull;

import com.intellij.lang.ASTNode;
import com.jetbrains.rest.validation.RestElementVisitor;

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
