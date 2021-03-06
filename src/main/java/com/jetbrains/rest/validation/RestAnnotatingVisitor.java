package com.jetbrains.rest.validation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.jetbrains.rest.RestFileType;
import com.jetbrains.rest.RestLanguage;
import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

/**
 * User : catherine
 */
public class RestAnnotatingVisitor implements Annotator {
  private static final Logger LOGGER = Logger.getInstance(RestAnnotatingVisitor.class.getName());
  private final List<RestAnnotator> myAnnotators = new ArrayList<RestAnnotator>();

  public RestAnnotatingVisitor() {
    for (Class<? extends RestAnnotator> cls : ((RestLanguage)RestFileType.INSTANCE.getLanguage()).getAnnotators()) {
      RestAnnotator annotator;
      try {
        annotator = cls.newInstance();
      }
      catch (InstantiationException e) {
        LOGGER.error(e);
        continue;
      }
      catch (IllegalAccessException e) {
        LOGGER.error(e);
        continue;
      }
      myAnnotators.add(annotator);
    }
  }

  public void annotate(@Nonnull PsiElement psiElement, @Nonnull AnnotationHolder holder) {
    for(RestAnnotator annotator: myAnnotators) {
      annotator.annotateElement(psiElement, holder);
    }
  }
}
