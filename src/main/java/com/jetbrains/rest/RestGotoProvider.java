package com.jetbrains.rest;

import com.jetbrains.rest.psi.RestReference;
import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.Editor;
import consulo.language.editor.navigation.GotoDeclarationHandlerBase;
import consulo.language.psi.PsiElement;
import consulo.language.psi.util.PsiTreeUtil;

/**
 * User : catherine
 */
@ExtensionImpl
public class RestGotoProvider extends GotoDeclarationHandlerBase {

  public PsiElement getGotoDeclarationTarget(PsiElement source, Editor editor) {
    if (source != null && source.getLanguage() instanceof RestLanguage) {
      RestReference ref = PsiTreeUtil.getParentOfType(source, RestReference.class);
      if (ref != null) {
        return ref.resolve();
      }
    }
    return null;
  }
}
