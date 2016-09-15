package com.jetbrains.rest.completion;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.patterns.StandardPatterns.or;

import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.jetbrains.rest.RestTokenTypes;
import com.jetbrains.rest.RestUtil;
import com.jetbrains.rest.psi.RestReferenceTarget;
import consulo.codeInsight.completion.CompletionProvider;

/**
 * User : catherine
 */
public class DirectiveCompletionContributor extends CompletionContributor
{
	public static final PsiElementPattern.Capture<PsiElement> DIRECTIVE_PATTERN = psiElement().afterSibling(or(psiElement().
			withElementType(RestTokenTypes.WHITESPACE).afterSibling(psiElement(RestReferenceTarget.class)), psiElement().withElementType(RestTokenTypes.EXPLISIT_MARKUP_START)));

	public DirectiveCompletionContributor()
	{
		extend(CompletionType.BASIC, DIRECTIVE_PATTERN, new CompletionProvider()
		{
			@Override
			public void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result)
			{
				for(String tag : RestUtil.getDirectives())
				{
					result.addElement(LookupElementBuilder.create(tag));
				}
			}
		});
	}
}
