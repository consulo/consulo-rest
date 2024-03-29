package com.jetbrains.rest.validation;

import consulo.language.editor.annotation.AnnotationHolder;
import consulo.language.psi.PsiElement;

/**
 * User : catherine
 */
public abstract class RestAnnotator extends RestElementVisitor {
  private AnnotationHolder _holder;

  public AnnotationHolder getHolder() {
    return _holder;
  }

  public void setHolder(AnnotationHolder holder) {
    _holder = holder;
  }

  public synchronized void annotateElement(final PsiElement psiElement, final AnnotationHolder holder) {
    setHolder(holder);
    try {
      psiElement.accept(this);
    }
    finally {
      setHolder(null);
    }
  }

  protected void markError(PsiElement element, String message) {
    getHolder().createErrorAnnotation(element, message);
  }
}
