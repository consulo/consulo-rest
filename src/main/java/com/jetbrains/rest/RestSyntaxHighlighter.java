/*
 * Copyright 2000-2008 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jetbrains.rest;

import com.jetbrains.rest.lexer.RestFlexLexer;
import consulo.codeEditor.DefaultLanguageHighlighterColors;
import consulo.colorScheme.TextAttributesKey;
import consulo.language.ast.IElementType;
import consulo.language.editor.highlight.SyntaxHighlighterBase;
import consulo.language.lexer.Lexer;

import jakarta.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * User : catherine
 */
public class RestSyntaxHighlighter extends SyntaxHighlighterBase implements RestTokenTypes {
  public static final TextAttributesKey REST_COMMENT = TextAttributesKey.createTextAttributesKey(
    "REST.LINE_COMMENT",
    DefaultLanguageHighlighterColors.LINE_COMMENT
  );

  public static final TextAttributesKey REST_SECTION_HEADER = TextAttributesKey.createTextAttributesKey(
    "REST.SECTION.HEADER",
    DefaultLanguageHighlighterColors.NUMBER
  );

  public static final TextAttributesKey REST_BOLD = TextAttributesKey.createTextAttributesKey(
    "REST.BOLD",
    DefaultLanguageHighlighterColors.IDENTIFIER
  );
  public static final TextAttributesKey REST_ITALIC = TextAttributesKey.createTextAttributesKey(
    "REST.ITALIC",
    DefaultLanguageHighlighterColors.IDENTIFIER
  );
  public static final TextAttributesKey REST_FIXED = TextAttributesKey.createTextAttributesKey(
    "REST.FIXED",
    DefaultLanguageHighlighterColors.IDENTIFIER
  );

  public static final TextAttributesKey REST_INTERPRETED = TextAttributesKey.createTextAttributesKey(
    "REST.INTERPRETED",
    DefaultLanguageHighlighterColors.IDENTIFIER
  );

  public static final TextAttributesKey REST_REF_NAME = TextAttributesKey.createTextAttributesKey(
    "REST.REF.NAME",
    DefaultLanguageHighlighterColors.STRING
  );

  public static final TextAttributesKey REST_EXPLICIT= TextAttributesKey.createTextAttributesKey(
    "REST.EXPLICIT",
    DefaultLanguageHighlighterColors.KEYWORD
  );
  public static final TextAttributesKey REST_FIELD = TextAttributesKey.createTextAttributesKey(
    "REST.FIELD",
    DefaultLanguageHighlighterColors.KEYWORD
  );

  public static final TextAttributesKey REST_INLINE = TextAttributesKey.createTextAttributesKey(
    "REST.INLINE",
    DefaultLanguageHighlighterColors.IDENTIFIER
  );
  private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<>();

  static {
    ATTRIBUTES.put(REFERENCE_NAME, REST_REF_NAME);
    ATTRIBUTES.put(DIRECT_HYPERLINK, REST_REF_NAME);
    ATTRIBUTES.put(TITLE, REST_SECTION_HEADER);
    ATTRIBUTES.put(TITLE_TEXT, REST_SECTION_HEADER);
    ATTRIBUTES.put(FOOTNOTE, REST_EXPLICIT);
    ATTRIBUTES.put(CITATION, REST_EXPLICIT);
    ATTRIBUTES.put(HYPERLINK, REST_REF_NAME);
    ATTRIBUTES.put(ANONYMOUS_HYPERLINK, REST_REF_NAME);
    ATTRIBUTES.put(DIRECTIVE, REST_EXPLICIT);
    ATTRIBUTES.put(CUSTOM_DIRECTIVE, REST_EXPLICIT);
    ATTRIBUTES.put(SUBSTITUTION, REST_EXPLICIT);
    ATTRIBUTES.put(COMMENT, REST_COMMENT);
    ATTRIBUTES.put(FIELD, REST_FIELD);
    ATTRIBUTES.put(BOLD, REST_BOLD);
    ATTRIBUTES.put(ITALIC, REST_ITALIC);
    ATTRIBUTES.put(FIXED, REST_FIXED);
    ATTRIBUTES.put(INTERPRETED, REST_INTERPRETED);
    ATTRIBUTES.put(INLINE_LINE, REST_INLINE);
    ATTRIBUTES.put(PYTHON_LINE, REST_INLINE);
    ATTRIBUTES.put(DJANGO_LINE, REST_INLINE);
  }


  @Nonnull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return SyntaxHighlighterBase.pack(ATTRIBUTES.get(tokenType));
  }

  @Nonnull
  public Lexer getHighlightingLexer() {
    return new RestFlexLexer();
  }
}
