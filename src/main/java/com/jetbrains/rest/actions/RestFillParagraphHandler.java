package com.jetbrains.rest.actions;

import com.jetbrains.rest.RestFile;
import com.jetbrains.rest.RestLanguage;
import com.jetbrains.rest.RestTokenTypes;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.action.ParagraphFillHandler;
import consulo.language.psi.PsiComment;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiWhiteSpace;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * User : ktisha
 */
@ExtensionImpl
public class RestFillParagraphHandler extends ParagraphFillHandler {

  @Nonnull
  protected String getPrefix(@Nonnull final PsiElement element) {
    return element instanceof PsiComment ? ".. " : "";
  }

  @Nonnull
  @Override
  protected String getPostfix(@Nonnull PsiElement element) {
    return element.getNode().getElementType() == RestTokenTypes.COMMENT? "\n" : "";
  }

  @Override
  public boolean isAvailableForFile(@Nullable PsiFile psiFile) {
    return psiFile instanceof RestFile;
  }

  @Override
  protected boolean isBunchOfElement(PsiElement element) {
    return true;
  }

  @Override
  public boolean atWhitespaceToken(@Nullable final PsiElement element) {
    return element instanceof PsiWhiteSpace ||
           element != null && element.getNode().getElementType() == RestTokenTypes.WHITESPACE;
  }

  @Override
  protected void appendPostfix(@Nonnull PsiElement element,
                               @Nonnull String text,
                               @Nonnull StringBuilder stringBuilder) {
    if (element.getNode().getElementType() == RestTokenTypes.COMMENT) {
      stringBuilder.append(getPostfix(element));
    }
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return RestLanguage.INSTANCE;
  }
}
