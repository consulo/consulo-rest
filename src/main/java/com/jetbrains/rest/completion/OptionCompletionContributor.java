package com.jetbrains.rest.completion;

import com.jetbrains.rest.RestLanguage;
import com.jetbrains.rest.RestUtil;
import com.jetbrains.rest.psi.RestDirectiveBlock;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.completion.*;
import consulo.language.editor.completion.lookup.LookupElementBuilder;
import consulo.language.pattern.PsiElementPattern;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.language.util.ProcessingContext;

import jakarta.annotation.Nonnull;

import static consulo.language.pattern.PlatformPatterns.psiElement;

/**
 * User : catherine
 */
@ExtensionImpl
public class OptionCompletionContributor extends CompletionContributor {
    public static final PsiElementPattern.Capture<PsiElement> OPTION_PATTERN = psiElement().withParent(RestDirectiveBlock.class);

    public OptionCompletionContributor() {
        extend(CompletionType.BASIC, OPTION_PATTERN, new CompletionProvider() {
            @Override
            public void addCompletions(@Nonnull CompletionParameters parameters, ProcessingContext context, @Nonnull CompletionResultSet result) {

                RestDirectiveBlock original = PsiTreeUtil.getParentOfType(parameters.getOriginalPosition(), RestDirectiveBlock.class);
                if (original != null) {

                    int offset = parameters.getOffset();
                    final PsiFile file = parameters.getOriginalFile();
                    String prefix = getPrefix(offset, file);

                    if (prefix.length() > 0) {
                        result = result.withPrefixMatcher(prefix);
                    }
                    for (String tag : RestUtil.getDirectiveOptions(original.getDirectiveName())) {
                        result.addElement(LookupElementBuilder.create(tag + " "));
                    }
                }

            }

            private String getPrefix(int offset, PsiFile file) {
                if (offset > 0) {
                    offset--;
                }
                final String text = file.getText();
                StringBuilder prefixBuilder = new StringBuilder();
                while (offset > 0 && (Character.isLetterOrDigit(text.charAt(offset)) || text.charAt(offset) == ':')) {
                    prefixBuilder.insert(0, text.charAt(offset));
                    if (text.charAt(offset) == ':') {
                        break;
                    }
                    offset--;
                }
                return prefixBuilder.toString();
            }
        });
    }

    @Nonnull
    @Override
    public Language getLanguage() {
        return RestLanguage.INSTANCE;
    }
}
