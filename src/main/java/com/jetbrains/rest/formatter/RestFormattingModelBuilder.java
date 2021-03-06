package com.jetbrains.rest.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.jetbrains.rest.RestLanguage;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * User : ktisha
 */
@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class RestFormattingModelBuilder implements FormattingModelBuilderEx, CustomFormattingModelBuilder {

  @Override
  public boolean isEngagedToFormat(PsiElement context) {
    PsiFile file = context.getContainingFile();
    return file != null && file.getLanguage() == RestLanguage.INSTANCE;
  }

  @Nullable
  @Override
  public CommonCodeStyleSettings.IndentOptions getIndentOptionsToUse(@Nonnull PsiFile file,
                                                                     @Nonnull FormatTextRanges ranges,
                                                                     @Nonnull CodeStyleSettings settings)
  {
    return null;
  }

  @Nonnull
  @Override
  public FormattingModel createModel(@Nonnull PsiElement element,
                                     @Nonnull CodeStyleSettings settings,
                                     @Nonnull FormattingMode mode) {

    final RestBlock block = new RestBlock(null, element.getNode(), null, Indent.getNoneIndent(), null);
    return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(), block, settings);
  }

  @Nonnull
  @Override
  public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
    return createModel(element, settings, FormattingMode.REFORMAT);
  }

  @Nullable
  @Override
  public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
    final PsiElement element = elementAtOffset.getPsi();
    final PsiElement container = element.getParent();
    return container != null ? container.getTextRange() : null;
  }
}
