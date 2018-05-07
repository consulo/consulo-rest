package com.jetbrains.rest.psi;

import javax.annotation.Nonnull;

import com.intellij.lang.ASTNode;

/**
 * User : ktisha
 */
public class RestFieldList extends RestElement {
  public RestFieldList(@Nonnull final ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return "RestFieldList:" + getNode().getElementType().toString();
  }

}
