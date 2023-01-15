package com.jetbrains.rest.psi;

import consulo.annotation.component.ExtensionImpl;
import consulo.document.util.TextRange;
import consulo.language.psi.AbstractElementManipulator;

import javax.annotation.Nonnull;

/**
 * User : ktisha
 */
@ExtensionImpl
public class RestLineManipulator extends AbstractElementManipulator<RestLine> {

  public RestLine handleContentChange(RestLine element, TextRange range, String newContent) {
    final String oldText = element.getText();
    final String newText = oldText.substring(0, range.getStartOffset()) + newContent + oldText.substring(range.getEndOffset());
    element.updateText(newText);
    return element;
  }

  @Nonnull
  @Override
  public Class<RestLine> getElementClass() {
    return RestLine.class;
  }

}
