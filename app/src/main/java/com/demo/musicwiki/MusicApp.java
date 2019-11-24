package com.demo.musicwiki;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.musicwiki.utils.LocaleManager;

import timber.log.Timber;

public class MusicApp extends Application implements Application.ActivityLifecycleCallbacks {
    private static Application application;
    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG)
        Timber.plant(new Timber.DebugTree());
        application = this;
    }

    public static Context getAppContext() {
        return application.getApplicationContext();
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
