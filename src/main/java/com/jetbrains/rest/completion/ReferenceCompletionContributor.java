package com.jetbrains.rest.completion;

import com.jetbrains.rest.RestLanguage;
import com.jetbrains.rest.RestTokenTypes;
import com.jetbrains.rest.psi.RestReference;
import com.jetbrains.rest.psi.RestReferenceTarget;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.completion.*;
import consulo.language.editor.completion.lookup.LookupElementBuilder;
import consulo.language.pattern.PsiElementPattern;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.language.util.ProcessingContext;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

import static consulo.language.pattern.StandardPatterns.psiElement;

/**
 * User : catherine
 */
@ExtensionImpl
public class ReferenceCompletionContributor extends CompletionContributor {
    public static final PsiElementPattern.Capture<PsiElement> REFERENCE_PATTERN = psiElement().afterSibling(psiElement().withElementType(RestTokenTypes.EXPLISIT_MARKUP_START));

    public static final PsiElementPattern.Capture<PsiElement> PATTERN = psiElement().andOr(psiElement().withParent(REFERENCE_PATTERN), REFERENCE_PATTERN);

    public ReferenceCompletionContributor() {
        extend(CompletionType.BASIC, PATTERN, new CompletionProvider() {
            @Override
            public void addCompletions(@Nonnull CompletionParameters parameters, ProcessingContext context, @Nonnull CompletionResultSet result) {
                PsiElement original = parameters.getPosition();
                PsiFile file = original.getContainingFile();
                int offset = parameters.getOffset();
                String prefix = getPrefix(offset, file);

                if (prefix.length() > 0) {
                    result = result.withPrefixMatcher(prefix);
                }
                RestReference[] elements = PsiTreeUtil.getChildrenOfType(file, RestReference.class);
                RestReferenceTarget[] targets = PsiTreeUtil.getChildrenOfType(file, RestReferenceTarget.class);
                Set<String> names = new HashSet<String>();
                if (targets != null) {
                    for (RestReferenceTarget t : targets) {
                        names.add(t.getReferenceName());
                    }
                }

                if (elements != null) {
                    for (RestReference e : elements) {
                        String name = e.getReferenceText();
                        if (!names.contains(name)) {
                            if ((name.startsWith("[") && name.endsWith("]")) || (name.startsWith("|") && name.endsWith("|"))) {
                                result.addElement(LookupElementBuilder.create(name));
                            }
                            else if (name.equals("__")) {
                                result.addElement(LookupElementBuilder.create(name + ":"));
                            }
                            else {
                                if (name.startsWith("_")) {
                                    name = "\\" + name;
                                }
                                result.addElement(LookupElementBuilder.create("_" + name + ":"));
                            }
                        }
                    }
                }
            }

            private String getPrefix(int offset, PsiFile file) {
                if (offset > 0) {
                    offset--;
                }
                final String text = file.getText();
                StringBuilder prefixBuilder = new StringBuilder();
                while (offset > 0 && (Character.isLetterOrDigit(text.charAt(offset)) || text.charAt(offset) == '_' || text.charAt(offset) == '[') || text.charAt(offset) == '|') {
                    prefixBuilder.insert(0, text.charAt(offset));
                    if (text.charAt(offset) == '_' || text.charAt(offset) == '[' || text.charAt(offset) == '|') {
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
