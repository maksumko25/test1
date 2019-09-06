/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.di;

import androidx.lifecycle.ViewModelProvider;

import com.test.keepit.test.viewmodel.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = ViewModelComponent.class)
class AppModule {
    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelComponent.Builder viewModelComponent) {
        return new ViewModelFactory(viewModelComponent.build());
    }
}
