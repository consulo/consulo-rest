package com.jetbrains.rest;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.highlight.SyntaxHighlighter;
import consulo.language.editor.highlight.SyntaxHighlighterFactory;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;

import javax.annotation.Nonnull;

/**
 * User : catherine
 */
@ExtensionImpl
public class RestHighlighterFactory extends SyntaxHighlighterFactory {

    @Nonnull
    @Override
    public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
        return new RestSyntaxHighlighter();
    }

    @Nonnull
    @Override
    public Language getLanguage() {
        return RestLanguage.INSTANCE;
    }
}
