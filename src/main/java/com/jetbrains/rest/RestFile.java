package com.jetbrains.rest;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.impl.source.PsiFileImpl;
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
