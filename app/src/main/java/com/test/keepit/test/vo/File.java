/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.vo;

import com.test.keepit.test.util.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class File {
    private int id;
    private String name;
    private long size;
    private Date date;

    public File(int id, String name, long size, Date date) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getFileSize() {
        return size;
    }

    public String getFormattedFileSize() {
        return Util.formatFileSize(size);
    }

    public long getDateTimeStamp() {
        return date.getTime();
    }

    public String getDate() {
        String format = "yyyy-MM-dd HH:mm:ss";
        return getDate(format);
    }

    public String getDate(String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
