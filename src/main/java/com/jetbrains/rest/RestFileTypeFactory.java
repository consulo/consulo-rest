package com.jetbrains.rest;

import consulo.annotation.component.ExtensionImpl;
import consulo.virtualFileSystem.fileType.FileTypeConsumer;
import consulo.virtualFileSystem.fileType.FileTypeFactory;

import javax.annotation.Nonnull;

/**
 * User : catherine
 */
@ExtensionImpl
public class RestFileTypeFactory extends FileTypeFactory {
  @Override
  public void createFileTypes(final @Nonnull FileTypeConsumer consumer) {
    consumer.consume(RestFileType.INSTANCE, RestFileType.DEFAULT_EXTENSION);
  }
}