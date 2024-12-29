package com.jetbrains.rest;

import consulo.language.ast.IElementType;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nonnull;

/**
 * User : catherine
 */
public class RestElementType extends IElementType {
    public RestElementType(@Nonnull @NonNls String s) {
        super(s, RestLanguage.INSTANCE);
    }
}
