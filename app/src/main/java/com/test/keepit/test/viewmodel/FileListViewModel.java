/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.test.keepit.test.repository.FileRepository;
import com.test.keepit.test.util.comparator.FileComparator;
import com.test.keepit.test.vo.File;
import com.test.keepit.test.vo.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class FileListViewModel extends AndroidViewModel {
    private MutableLiveData<Sort> sortingType = new MutableLiveData<>();
    private MutableLiveData<List<File>> filesList = new MutableLiveData<>();

    private final FileRepository fileRepository;

    @Inject
    public FileListViewModel(@NonNull FileRepository fileRepository, @NonNull Application application) {
        super(application);
        this.fileRepository = fileRepository;
    }

    public LiveData<List<File>> getFileList() {
        return Transformations.switchMap(sortingType, sort -> {
            List<File> dataList = new ArrayList<>(fileRepository.getFileList());
            Collections.sort(dataList, new FileComparator(sort));

            filesList.setValue(dataList);
            return filesList;
        });
    }

    public void sortData(Sort sort) {
        sortingType.setValue(sort);
    }

    public LiveData<Sort> getCurrentSort(){
        return sortingType;
    }
}
