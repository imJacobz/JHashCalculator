package com.jacobz.jhash.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {
    private static final String RESOURCE_NAME = "res";

    public static ResourceBundle getBundle(Locale locale) {
        if (locale == null) {
            locale = new Locale("en", "US");
        }

        return ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

}
