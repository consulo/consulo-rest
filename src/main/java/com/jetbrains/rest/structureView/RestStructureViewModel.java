package com.jetbrains.rest.structureView;

import com.jetbrains.rest.RestFile;
import com.jetbrains.rest.psi.RestTitle;
import consulo.fileEditor.structureView.StructureViewModel;
import consulo.fileEditor.structureView.StructureViewTreeElement;
import consulo.fileEditor.structureView.tree.Sorter;
import consulo.language.editor.structureView.StructureViewModelBase;
import consulo.language.psi.PsiFile;

import jakarta.annotation.Nonnull;

/**
 * User : catherine
 */
public class RestStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider, StructureViewModel.ExpandInfoProvider {
  public RestStructureViewModel(@Nonnull PsiFile psiFile) {
    super(psiFile, new RestStructureViewElement(psiFile));
    withSorters(Sorter.ALPHA_SORTER);
    withSuitableClasses(RestTitle.class);
  }

  @Override
  public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
    final Object value = element.getValue();
    return value instanceof RestFile;
  }

  @Override
  public boolean isAlwaysLeaf(StructureViewTreeElement element) {
    return element.getValue() instanceof RestTitle;
  }

  @Override
  public boolean isAutoExpand(StructureViewTreeElement element) {
    return element.getValue() instanceof PsiFile;
  }

  @Override
  public boolean isSmartExpand() {
    return false;
  }
}
