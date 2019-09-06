/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.test.keepit.test.R;
import com.test.keepit.test.databinding.FileListItemBinding;
import com.test.keepit.test.view.callback.FileClickCallback;
import com.test.keepit.test.vo.File;

public class FileAdapter extends DataBoundListAdapter<File, FileListItemBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final LayoutInflater inflater;
    private final FileClickCallback clickCallback;


    public FileAdapter(DataBindingComponent dataBindingComponent, Context context, FileClickCallback clickCallback) {
        this.dataBindingComponent = dataBindingComponent;
        this.inflater = LayoutInflater.from(context);
        this.clickCallback = clickCallback;
    }

    @Override
    protected ViewDataBinding createBinding(ViewGroup parent, int viewType) {
        return DataBindingUtil.<FileListItemBinding>inflate(inflater, R.layout.file_list_item, parent, false, dataBindingComponent);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    protected void bind(ViewDataBinding binding, File newItem, int position) {
        FileListItemBinding rowBinding = (FileListItemBinding) binding;
        rowBinding.setFile(newItem);
        rowBinding.setCallback(clickCallback);
    }

    @Override
    protected boolean areItemsTheSame(File oldItem, File newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    protected boolean areContentsTheSame(File oldItem, File newItem) {
        return oldItem.getName().equals(newItem.getName())
                && oldItem.getFileSize() == newItem.getFileSize()
                && oldItem.getDateTimeStamp() == newItem.getDateTimeStamp();
    }
}