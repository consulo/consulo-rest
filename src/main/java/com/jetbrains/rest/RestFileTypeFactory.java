package com.jetbrains.rest;

import javax.annotation.Nonnull;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;

/**
 * User : catherine
 */
public class RestFileTypeFactory extends FileTypeFactory {
  @Override
  public void createFileTypes(final @Nonnull FileTypeConsumer consumer) {
    consumer.consume(RestFileType.INSTANCE, RestFileType.DEFAULT_EXTENSION);
  }
}