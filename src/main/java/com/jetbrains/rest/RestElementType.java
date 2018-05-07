package com.jetbrains.rest;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;

/**
 * User : catherine
 */
public class RestElementType extends IElementType {
    public RestElementType(@Nonnull @NonNls String s) {
        super(s, RestLanguage.INSTANCE);
    }
}
