/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.di;

import com.test.keepit.test.view.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();
}
