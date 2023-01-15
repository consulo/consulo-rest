package com.jetbrains.rest.validation;

import com.jetbrains.rest.RestLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.annotation.Annotator;
import consulo.language.editor.annotation.AnnotatorFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 15/01/2023
 */
@ExtensionImpl
public class RestAnnotatorFactory implements AnnotatorFactory {
    @Nullable
    @Override
    public Annotator createAnnotator() {
        return new RestAnnotatingVisitor();
    }

    @Nonnull
    @Override
    public Language getLanguage() {
        return RestLanguage.INSTANCE;
    }
}
