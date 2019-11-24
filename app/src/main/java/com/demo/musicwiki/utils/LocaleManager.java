package com.demo.musicwiki.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

import static com.demo.musicwiki.utils.Constants.ENGLISH;
import static com.demo.musicwiki.utils.Constants.KEY_PREFERRED_LANGUAGE;

public class LocaleManager {

    public static Context setLocale(Context context) {
        return updateResources(context, getLanguage(context));
    }

    public static void setNewLocale(Context c, String language) {
        persistLanguage(c, language);
        updateResources(c, language);
    }

    public static String getLanguage(Context c) {
        return SharedPreferenceHelper.readFromSharedPreference(c, KEY_PREFERRED_LANGUAGE, ENGLISH);
    }

    private static void persistLanguage(Context c, String language) {
        SharedPreferenceHelper.writeToSharedPreference(c, KEY_PREFERRED_LANGUAGE, language);
    }


    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
        return context;
    }
}
