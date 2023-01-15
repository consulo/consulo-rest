package com.jetbrains.rest.psi;

import consulo.language.ast.ASTNode;

import javax.annotation.Nonnull;

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
