package com.jetbrains.rest.lexer;

import com.jetbrains.rest.RestTokenTypes;
import consulo.language.ast.TokenSet;
import consulo.language.lexer.MergingLexerAdapter;

/**
 * User : catherine
 */
public class RestFlexLexer extends MergingLexerAdapter {
  public static final TokenSet TOKENS_TO_MERGE = TokenSet.create(RestTokenTypes.ITALIC, RestTokenTypes.BOLD, RestTokenTypes.FIXED,
                                                           RestTokenTypes.LINE, RestTokenTypes.PYTHON_LINE,
                                                           RestTokenTypes.COMMENT, RestTokenTypes.INLINE_LINE,
                                                           RestTokenTypes.WHITESPACE);
  public RestFlexLexer() {
    super(new _RestFlexLexer(), TOKENS_TO_MERGE);
  }
}
