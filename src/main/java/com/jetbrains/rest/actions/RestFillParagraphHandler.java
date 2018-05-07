package com.jetbrains.rest.actions;

import javax.annotation.Nonnull;

import com.intellij.codeInsight.editorActions.fillParagraph.ParagraphFillHandler;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.rest.RestFile;
import com.jetbrains.rest.RestTokenTypes;

import javax.annotation.Nullable;

/**
 * User : ktisha
 */
public class RestFillParagraphHandler extends ParagraphFillHandler {

  @Nonnull
  protected String getPrefix(@Nonnull final PsiElement element) {
    return element instanceof PsiComment? ".. " : "";
  }

  @Nonnull
  @Override
  protected String getPostfix(@Nonnull PsiElement element) {
    return element.getNode().getElementType() == RestTokenTypes.COMMENT? "\n" : "";
  }

  @Override
  protected boolean isAvailableForFile(@Nullable PsiFile psiFile) {
    return psiFile instanceof RestFile;
  }

  @Override
  protected boolean isBunchOfElement(PsiElement element) {
    return true;
  }

  @Override
  protected boolean atWhitespaceToken(@Nullable final PsiElement element) {
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
}
