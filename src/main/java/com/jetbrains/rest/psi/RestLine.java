package com.jetbrains.rest.psi;

import consulo.document.util.TextRange;
import consulo.language.ast.ASTNode;
import consulo.language.impl.ast.LeafElement;
import consulo.language.psi.LiteralTextEscaper;
import consulo.language.psi.PsiLanguageInjectionHost;

import jakarta.annotation.Nonnull;

/**
 * User : ktisha
 */
public class RestLine extends RestElement implements PsiLanguageInjectionHost {
  public RestLine(@Nonnull final ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return "RestLine:" + getNode().getElementType().toString();
  }


  @Override
  public boolean isValidHost() {
    return true;
  }

  @Override
  public PsiLanguageInjectionHost updateText(@Nonnull String text) {
    ASTNode valueNode = getNode().getFirstChildNode();
    assert valueNode instanceof LeafElement;
    ((LeafElement)valueNode).replaceWithText(text);
    return this;
  }

  @Nonnull
  @Override
  public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
    return new LiteralTextEscaper<PsiLanguageInjectionHost>(this) {
      @Override
      public boolean decode(@Nonnull TextRange rangeInsideHost, @Nonnull StringBuilder outChars) {
        outChars.append(rangeInsideHost.substring(myHost.getText()));
        return true;
      }

      @Override
      public int getOffsetInHost(int offsetInDecoded, @Nonnull TextRange rangeInsideHost) {
        int offset = offsetInDecoded + rangeInsideHost.getStartOffset();
        if (offset < rangeInsideHost.getStartOffset()) offset = rangeInsideHost.getStartOffset();
        if (offset > rangeInsideHost.getEndOffset()) offset = rangeInsideHost.getEndOffset();
        return offset;
      }

      @Override
      public boolean isOneLine() {
        return false;
      }
    };
  }
}
