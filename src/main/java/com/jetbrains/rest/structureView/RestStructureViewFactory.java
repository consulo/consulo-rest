package com.jetbrains.rest.structureView;

import com.jetbrains.rest.RestLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.Editor;
import consulo.fileEditor.structureView.StructureViewBuilder;
import consulo.fileEditor.structureView.StructureViewModel;
import consulo.fileEditor.structureView.TreeBasedStructureViewBuilder;
import consulo.language.Language;
import consulo.language.editor.structureView.PsiStructureViewFactory;
import consulo.language.psi.PsiFile;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * User : catherine
 */
@ExtensionImpl
public class RestStructureViewFactory implements PsiStructureViewFactory {
  @Override
  public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
    return new TreeBasedStructureViewBuilder() {

      @Override
      @Nonnull
      public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
        return new RestStructureViewModel(psiFile);
      }
    };
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return RestLanguage.INSTANCE;
  }
}
