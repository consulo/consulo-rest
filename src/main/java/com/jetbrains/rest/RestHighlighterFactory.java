package com.jetbrains.rest;

import javax.annotation.Nonnull;

import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * User : catherine
 */
public class RestHighlighterFactory extends SyntaxHighlighterFactory {

    @Nonnull
    @Override
    public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
        return new RestSyntaxHighlighter();
    }
}
