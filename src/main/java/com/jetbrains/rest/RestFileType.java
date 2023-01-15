package com.jetbrains.rest;

import consulo.language.file.LanguageFileType;
import consulo.localize.LocalizeValue;
import consulo.reStructuredText.icon.ReStructuredTextIconGroup;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * User : catherine
 *
 * file type for restructured text files
 */
public class RestFileType extends LanguageFileType {
  public static final RestFileType INSTANCE = new RestFileType();
  public static final String DEFAULT_EXTENSION = "rst";

  private RestFileType() {
    super(RestLanguage.INSTANCE);
  }

  @Nonnull
  public String getId() {
    return "ReST";
  }

  @Nonnull
  public LocalizeValue getDescription() {
    return LocalizeValue.localizeTODO("reStructuredText files");
  }

  @Nonnull
  public String getDefaultExtension() {
    return DEFAULT_EXTENSION;
  }

  @Nullable
  public Image getIcon() {
    return ReStructuredTextIconGroup.rst();
  }
}

