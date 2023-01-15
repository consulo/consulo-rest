package com.jetbrains.rest.formatter;

import com.jetbrains.rest.RestLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.document.util.TextRange;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.codeStyle.*;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * User : ktisha
 */
@ExtensionImpl
public class RestFormattingModelBuilder implements FormattingModelBuilder, CustomFormattingModelBuilder {

  @Override
  public boolean isEngagedToFormat(PsiElement context) {
    PsiFile file = context.getContainingFile();
    return file != null && file.getLanguage() == RestLanguage.INSTANCE;
  }

  @Nonnull
  @Override
  public FormattingModel createModel(@Nonnull FormattingContext formattingContext) {
    PsiElement element = formattingContext.getPsiElement();
    CodeStyleSettings settings = formattingContext.getCodeStyleSettings();
    final RestBlock block = new RestBlock(null, element.getNode(), null, Indent.getNoneIndent(), null);
    return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(), block, settings);
  }

  @Nullable
  @Override
  public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
    final PsiElement element = elementAtOffset.getPsi();
    final PsiElement container = element.getParent();
    return container != null ? container.getTextRange() : null;
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return RestLanguage.INSTANCE;
  }
}
