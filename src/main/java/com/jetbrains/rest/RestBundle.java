package com.jetbrains.rest;

import consulo.component.util.localize.AbstractBundle;
import org.jetbrains.annotations.PropertyKey;

/**
 * User : catherine
 */
public class RestBundle extends AbstractBundle {
    private static final String BUNDLE = "com.jetbrains.rest.RestBundle";
    private static final RestBundle ourInstance = new RestBundle();

    private RestBundle() {
        super(BUNDLE);
    }

    public static String message(@PropertyKey(resourceBundle = BUNDLE) String key) {
        return ourInstance.getMessage(key);
    }

    public static String message(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return ourInstance.getMessage(key, params);
    }
}
