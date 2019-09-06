/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.di;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.test.keepit.test.MainApplication;

import dagger.android.AndroidInjection;
import dagger.android.support.HasSupportFragmentInjector;


public class AppInjector {
    private AppInjector() {}

    public static void init(MainApplication application) {
        DaggerAppComponent.builder().application(application)
                .build().inject(application);

        application
                .registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                        handleActivity(activity);
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {

                    }

                    @Override
                    public void onActivityResumed(Activity activity) {

                    }

                    @Override
                    public void onActivityPaused(Activity activity) {

                    }

                    @Override
                    public void onActivityStopped(Activity activity) {

                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {

                    }
                });
    }

    private static void handleActivity(Activity activity) {
        if (activity instanceof HasSupportFragmentInjector) {
            AndroidInjection.inject(activity);
        }
    }
}
