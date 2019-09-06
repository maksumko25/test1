/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.view.ui;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.test.keepit.test.R;
import com.test.keepit.test.binding.ActivityDataBindingComponent;
import com.test.keepit.test.databinding.ActivityMainBinding;
import com.test.keepit.test.view.adapter.FileAdapter;
import com.test.keepit.test.view.callback.FileClickCallback;
import com.test.keepit.test.view.callback.SortCallback;
import com.test.keepit.test.view.custom.SortView;
import com.test.keepit.test.viewmodel.FileListViewModel;
import com.test.keepit.test.vo.Sort;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.test.keepit.test.util.SortDirection.ASC;
import static com.test.keepit.test.util.SortType.DATE;
import static com.test.keepit.test.util.SortType.NAME;
import static com.test.keepit.test.util.SortType.SIZE;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    private static final String TAG = "MainActivity";

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityDataBindingComponent dataBindingComponent = new ActivityDataBindingComponent();
    private ActivityMainBinding binding;

    private FileAdapter fileAdapter;
    FileListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        fileAdapter = new FileAdapter(dataBindingComponent, this, clickCallback);
        binding.dataList.setAdapter(fileAdapter);
        binding.dataList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FileListViewModel.class);
        observeViewModel(viewModel);

        Sort sortName = new Sort(ASC, NAME);
        binding.header.sortName.setSort(sortName);

        Sort sortSize = new Sort(ASC, SIZE);
        binding.header.sortSize.setSort(sortSize);

        Sort sortDate = new Sort(ASC, DATE);
        binding.header.sortDate.setSort(sortDate);

        binding.header.sortName.setSortCallback(sortCallback);
        binding.header.sortSize.setSortCallback(sortCallback);
        binding.header.sortDate.setSortCallback(sortCallback);

        //  init data
        viewModel.sortData(sortName);
    }

    private void observeViewModel(FileListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getFileList().observe(this, files -> {
            fileAdapter.replace(files);

            //  It's used for scrolling to top after sorting
            new Handler().postDelayed(() -> binding.dataList.scrollToPosition(0), 300);
        });

        viewModel.getCurrentSort().observe(this, sort -> {
            //  Check arrow animations in sorting views
            ViewGroup viewGroup = (ViewGroup) binding.header.getRoot();
            int count = viewGroup.getChildCount();

            for (int i = 0; i < count; i++) {
                View view = viewGroup.getChildAt(i);
                if (view instanceof SortView){
                    if(sort.getType() == ((SortView) view).getSort().getType()){
                        ((SortView) view).animateArrow(View.VISIBLE);
                    } else {
                        ((SortView) view).animateArrow(View.INVISIBLE);
                    }
                }
            }
        });
    }

    private final SortCallback sortCallback = new SortCallback() {
        @Override
        public void onSort(Sort sort) {
            Log.d(TAG, "Sort clicked: " + sort.getName(getApplicationContext()));
            viewModel.sortData(sort);
        }
    };

    private final FileClickCallback clickCallback = file -> Log.d(TAG, "Row clicked: " + file.getName());

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
