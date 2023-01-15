package com.jetbrains.rest;

import com.jetbrains.rest.validation.RestAnnotator;
import com.jetbrains.rest.validation.RestHyperlinksAnnotator;
import com.jetbrains.rest.validation.RestInlineBlockAnnotator;
import com.jetbrains.rest.validation.RestReferenceTargetAnnotator;
import consulo.language.Language;
import consulo.language.template.TemplateLanguage;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Supplier;

/**
 * User : catherine
 */
public class RestLanguage extends Language implements TemplateLanguage {
  public static final RestLanguage INSTANCE = new RestLanguage();
  private final Set<Supplier<RestAnnotator>> _annotators = new CopyOnWriteArraySet<>();
  private RestLanguage() {
    super("ReST");
  }

  @Override
  public String getDisplayName() {
    return "Rest language";
  }

  @Override
  public RestFileType getAssociatedFileType() {
    return RestFileType.INSTANCE;
  }
  {
    _annotators.add(RestHyperlinksAnnotator::new);
    _annotators.add(RestReferenceTargetAnnotator::new);
    _annotators.add(RestInlineBlockAnnotator::new);
  }

  public Set<Supplier<RestAnnotator>> getAnnotators() {
    return _annotators;
  }
}
