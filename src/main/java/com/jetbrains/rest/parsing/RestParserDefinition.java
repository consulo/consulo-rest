package com.jetbrains.rest.parsing;

import com.jetbrains.rest.RestFile;
import com.jetbrains.rest.RestLanguage;
import com.jetbrains.rest.RestTokenTypes;
import com.jetbrains.rest.lexer.RestFlexLexer;
import com.jetbrains.rest.psi.RestASTFactory;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IFileElementType;
import consulo.language.ast.TokenSet;
import consulo.language.file.FileViewProvider;
import consulo.language.lexer.Lexer;
import consulo.language.parser.ParserDefinition;
import consulo.language.parser.PsiParser;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.version.LanguageVersion;

import jakarta.annotation.Nonnull;

/**
 * User : catherine
 */
@ExtensionImpl
public class RestParserDefinition implements ParserDefinition, RestTokenTypes {
  private static final IFileElementType FILE_ELEMENT_TYPE = new IFileElementType(RestLanguage.INSTANCE);

  private final RestASTFactory astFactory = new RestASTFactory();

  @Nonnull
  @Override
  public Language getLanguage() {
    return RestLanguage.INSTANCE;
  }

  @Nonnull
  @Override
  public Lexer createLexer(LanguageVersion languageVersion) {
    return new RestFlexLexer();
  }

  @Override
  public PsiParser createParser(LanguageVersion languageVersion) {
    return new RestParser();
  }

  @Override
  public IFileElementType getFileNodeType() {
    return FILE_ELEMENT_TYPE;
  }

  @Nonnull
  @Override
  public TokenSet getWhitespaceTokens(LanguageVersion languageVersion) {
    return TokenSet.EMPTY;
  }

  @Nonnull
  @Override
  public TokenSet getCommentTokens(LanguageVersion languageVersion) {
    return TokenSet.EMPTY;
  }

  @Nonnull
  @Override
  public TokenSet getStringLiteralElements(LanguageVersion languageVersion) {
    return TokenSet.EMPTY;
  }

  @Nonnull
  @Override
  public PsiElement createElement(ASTNode node) {
    return astFactory.create(node);
  }

  @Override
  public PsiFile createFile(FileViewProvider viewProvider) {
    return new RestFile(viewProvider);
  }

  @Override
  public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }
}
