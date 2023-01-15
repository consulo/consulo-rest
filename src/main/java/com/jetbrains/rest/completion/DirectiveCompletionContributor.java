package com.jetbrains.rest.completion;

import com.jetbrains.rest.RestLanguage;
import com.jetbrains.rest.RestTokenTypes;
import com.jetbrains.rest.RestUtil;
import com.jetbrains.rest.psi.RestReferenceTarget;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.completion.*;
import consulo.language.editor.completion.lookup.LookupElementBuilder;
import consulo.language.pattern.PsiElementPattern;
import consulo.language.psi.PsiElement;
import consulo.language.util.ProcessingContext;

import javax.annotation.Nonnull;

import static consulo.language.pattern.PlatformPatterns.psiElement;
import static consulo.language.pattern.StandardPatterns.or;

/**
 * User : catherine
 */
@ExtensionImpl
public class DirectiveCompletionContributor extends CompletionContributor {
    public static final PsiElementPattern.Capture<PsiElement> DIRECTIVE_PATTERN = psiElement().afterSibling(or(psiElement().
            withElementType(RestTokenTypes.WHITESPACE).afterSibling(psiElement(RestReferenceTarget.class)), psiElement().withElementType(RestTokenTypes.EXPLISIT_MARKUP_START)));

    public DirectiveCompletionContributor() {
        extend(CompletionType.BASIC, DIRECTIVE_PATTERN, new CompletionProvider() {
            @Override
            public void addCompletions(@Nonnull CompletionParameters parameters, ProcessingContext context, @Nonnull CompletionResultSet result) {
                for (String tag : RestUtil.getDirectives()) {
                    result.addElement(LookupElementBuilder.create(tag));
                }
            }
        });
    }

    @Nonnull
    @Override
    public Language getLanguage() {
        return RestLanguage.INSTANCE;
    }
}
