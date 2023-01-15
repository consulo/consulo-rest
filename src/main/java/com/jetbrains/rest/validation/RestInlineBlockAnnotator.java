package com.jetbrains.rest.validation;

import com.jetbrains.rest.RestBundle;
import com.jetbrains.rest.psi.RestInlineBlock;
import consulo.document.util.TextRange;
import consulo.language.psi.PsiElement;

/**
 * Looks for invalid inline block
 *
 * User : catherine
 */
public class RestInlineBlockAnnotator extends RestAnnotator {

  @Override
  public void visitInlineBlock(final RestInlineBlock node) {
    if (!node.validBlock()) {
      PsiElement el = node.getLastChild();
      if (el != null) {
        final int endOffset = node.getTextRange().getEndOffset();
        getHolder().createErrorAnnotation(TextRange.create(endOffset-1, endOffset), RestBundle.message("ANN.inline.block"));
      }
    }
  }
}
