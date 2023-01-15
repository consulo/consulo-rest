package com.jetbrains.rest;

import consulo.language.file.FileViewProvider;
import consulo.language.impl.psi.PsiFileImpl;
import consulo.language.psi.PsiElementVisitor;
import consulo.virtualFileSystem.fileType.FileType;

import javax.annotation.Nonnull;

/**
 * User : catherine
 */
public class RestFile extends PsiFileImpl {
  public RestFile(FileViewProvider viewProvider) {
    super(RestElementTypes.REST_FILE, RestElementTypes.REST_FILE, viewProvider);
  }

  @Nonnull
  public FileType getFileType() {
    return RestFileType.INSTANCE;
  }

  @Override
  public void accept(@Nonnull PsiElementVisitor visitor) {
    visitor.visitFile(this);
  }

  @Override
  public String toString() {
    return "rest file";
  }
}
