package com.jetbrains.rest.psi;

import com.jetbrains.rest.RestElementTypes;
import com.jetbrains.rest.RestTokenTypes;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IElementType;
import consulo.language.impl.psi.ASTWrapperPsiElement;
import consulo.language.psi.PsiElement;

/**
 * User : catherine
 */
public class RestASTFactory implements RestTokenTypes, RestElementTypes {

  public PsiElement create(ASTNode node) {
    IElementType type = node.getElementType();

    if (type == DIRECTIVE_BLOCK) {
      return new RestDirectiveBlock(node);
    }
    if (type == REFERENCE_NAME) {
      return new RestReference(node);
    }
    if (type == REFERENCE_TARGET) {
      return new RestReferenceTarget(node);
    }
    if (type == TITLE) {
      return new RestTitle(node);
    }
    if (type == FIELD) {
      return new RestRole(node);
    }
    if (type == FIELD_LIST) {
      return new RestFieldList(node);
    }
    if (type == INLINE_BLOCK) {
      return new RestInlineBlock(node);
    }
    if (type == LINE_TEXT) {
      return new RestLine(node);
    }
    return new ASTWrapperPsiElement(node);
  }
}
