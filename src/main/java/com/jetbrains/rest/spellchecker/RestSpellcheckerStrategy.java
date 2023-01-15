package com.jetbrains.rest.spellchecker;

import com.jetbrains.rest.RestLanguage;
import com.jetbrains.rest.RestTokenTypes;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.ast.IElementType;
import consulo.language.impl.psi.LeafPsiElement;
import consulo.language.psi.PsiElement;
import consulo.language.spellcheker.SpellcheckingStrategy;
import consulo.language.spellcheker.tokenizer.TokenConsumer;
import consulo.language.spellcheker.tokenizer.Tokenizer;
import consulo.language.spellcheker.tokenizer.splitter.PlainTextTokenSplitter;

import javax.annotation.Nonnull;

/**
 * @author yole
 */
@ExtensionImpl
public class RestSpellcheckerStrategy extends SpellcheckingStrategy {
  private static final Tokenizer<PsiElement> REST_ELEMENT_TOKENIZER = new Tokenizer<PsiElement>() {
    @Override
    public void tokenize(@Nonnull PsiElement element, TokenConsumer consumer) {
      consumer.consumeToken(element, PlainTextTokenSplitter.getInstance());
    }
  };

  @Nonnull
  @Override
  public Tokenizer getTokenizer(PsiElement element) {
    IElementType elementType = element.getNode().getElementType();
    if (elementType == RestTokenTypes.FIELD ||
        elementType == RestTokenTypes.CUSTOM_DIRECTIVE ||
        elementType == RestTokenTypes.REST_DJANGO_INJECTION ||
        elementType == RestTokenTypes.REST_INJECTION) {
      return EMPTY_TOKENIZER;
    }
    if (element instanceof LeafPsiElement && element.getLanguage() == RestLanguage.INSTANCE) {
      return REST_ELEMENT_TOKENIZER;
    }
    return EMPTY_TOKENIZER;
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return RestLanguage.INSTANCE;
  }
}
