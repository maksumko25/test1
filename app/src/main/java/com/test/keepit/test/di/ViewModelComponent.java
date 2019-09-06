/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.di;

import com.test.keepit.test.viewmodel.FileListViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelComponent build();
    }

    FileListViewModel fileListViewModel();
}
