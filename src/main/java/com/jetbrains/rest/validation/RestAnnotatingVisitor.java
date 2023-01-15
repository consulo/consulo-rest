package com.jetbrains.rest.validation;

import com.jetbrains.rest.RestFileType;
import com.jetbrains.rest.RestLanguage;
import consulo.language.editor.annotation.AnnotationHolder;
import consulo.language.editor.annotation.Annotator;
import consulo.language.psi.PsiElement;
import consulo.logging.Logger;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * User : catherine
 */
public class RestAnnotatingVisitor implements Annotator {
  private static final Logger LOGGER = Logger.getInstance(RestAnnotatingVisitor.class);
  private final List<RestAnnotator> myAnnotators = new ArrayList<RestAnnotator>();

  public RestAnnotatingVisitor() {
    for (Supplier<? extends RestAnnotator> supplier : ((RestLanguage)RestFileType.INSTANCE.getLanguage()).getAnnotators()) {
      myAnnotators.add(supplier.get());
    }
  }

  public void annotate(@Nonnull PsiElement psiElement, @Nonnull AnnotationHolder holder) {
    for(RestAnnotator annotator: myAnnotators) {
      annotator.annotateElement(psiElement, holder);
    }
  }
}
