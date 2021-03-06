package com.jetbrains.rest;

import com.intellij.openapi.fileTypes.LanguageFileType;
import consulo.reStructuredText.icon.ReStructuredTextIconGroup;
import consulo.ui.image.Image;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * User : catherine
 *
 * file type for restructured text files
 */
public class RestFileType extends LanguageFileType {
  public static final RestFileType INSTANCE = new RestFileType();
  @NonNls public static final String DEFAULT_EXTENSION = "rst";
  @NonNls private static final String NAME = "ReST";
  @NonNls private static final String DESCRIPTION = "reStructuredText files";

  private RestFileType() {
    super(RestLanguage.INSTANCE);
  }

  @Nonnull
  public String getId() {
    return NAME;
  }

  @Nonnull
  public String getDescription() {
    return DESCRIPTION;
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

