package com.jetbrains.rest;

import consulo.annotation.component.ExtensionImpl;
import consulo.colorScheme.TextAttributesKey;
import consulo.colorScheme.setting.AttributesDescriptor;
import consulo.language.editor.colorScheme.setting.ColorSettingsPage;
import consulo.language.editor.highlight.SyntaxHighlighter;
import consulo.language.editor.highlight.SyntaxHighlighterFactory;
import consulo.localize.LocalizeValue;
import consulo.reStructuredText.localize.RestLocalize;
import jakarta.annotation.Nonnull;

import java.util.HashMap;
import java.util.Map;

/**
 * User : catherine
 */
@ExtensionImpl
public class RestColorsPage implements ColorSettingsPage {
  private static final AttributesDescriptor[] ATTRS = new AttributesDescriptor[]{
    new AttributesDescriptor("Comment", RestSyntaxHighlighter.REST_COMMENT),
    new AttributesDescriptor("Title", RestSyntaxHighlighter.REST_SECTION_HEADER),
    new AttributesDescriptor("Explicit markup", RestSyntaxHighlighter.REST_EXPLICIT),
    new AttributesDescriptor("Fields", RestSyntaxHighlighter.REST_FIELD),
    new AttributesDescriptor("Reference name", RestSyntaxHighlighter.REST_REF_NAME),
    new AttributesDescriptor("Inline literals", RestSyntaxHighlighter.REST_FIXED),
    new AttributesDescriptor("Bold text", RestSyntaxHighlighter.REST_BOLD),
    new AttributesDescriptor("Italic text", RestSyntaxHighlighter.REST_ITALIC),
    new AttributesDescriptor("Interpreted text", RestSyntaxHighlighter.REST_INTERPRETED),
    new AttributesDescriptor("Literal and line blocks", RestSyntaxHighlighter.REST_INLINE),
  };

  private static final HashMap<String, TextAttributesKey> ourTagToDescriptorMap = new HashMap<String, TextAttributesKey>();


  @Nonnull
  public LocalizeValue getDisplayName() {
    return RestLocalize.restText();
  }

  @Nonnull
  public AttributesDescriptor[] getAttributeDescriptors() {
    return ATTRS;
  }

  @Nonnull
  public SyntaxHighlighter getHighlighter() {
    final SyntaxHighlighter highlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(RestFileType.INSTANCE, null, null);
    assert highlighter != null;
    return highlighter;
  }

  @Nonnull
  public String getDemoText() {
    return
      ".. comment for documentation master file\n\n" +
      "===============\n" +
      " Section Title\n" +
      "===============\n\n" +
      ".. toctree::\n" +
      "   :maxdepth: 2\n\n" +
      "There is *some italics text*\n" +
      "and **bold one** " +
      "and ``inline literals``\n\n" +
      "A link_ in citation style.\n" +
      "\n" +
      ".. _link: http://www.google.com\n\n" +
      ".. rubric:: Footnotes\n" +
      ".. [*] footnote.\n" +
      ".. [REL09] Citation\n\n" +
      ":ref:`builders`\n\n" +
      "\n" +
      "::\n" +
      "\n" +
      "  Whitespace, newlines, blank lines, and\n" +
      "  all kinds of markup (like *this* or\n" +
      "  \\this) is preserved by literal blocks.\n\n" +
      "It was literal block.";
  }

  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return ourTagToDescriptorMap;
  }
}
