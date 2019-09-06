/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.repository;

import com.test.keepit.test.vo.File;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FileRepository {
    private final List<File> fileList = new ArrayList<>();

    @Inject
    public FileRepository() {
        //  Generating fake data

        int sizeMin = 1024;
        int sizeMax = 1024 * 1024 * 1024;

        long beginTime = Timestamp.valueOf("2000-01-01 00:00:00").getTime();
        long endTime = Timestamp.valueOf("2018-12-31 00:00:00").getTime();

        //  change this variable if you need more or less objects
        int filesCount = 10;

        for (int i = 0; i < filesCount; i++) {
            String name = String.format("Name %d", i);

            //  Generating random size
            Random rand = new Random();
            int randomNum = rand.nextInt((sizeMax - sizeMin) + 1) + sizeMin;
            long size = (long) randomNum;

            //  Generating random date
            Date date = new Date(getRandomTimeBetweenTwoDates(beginTime, endTime));

            File file = new File(i, name, size, date);

            fileList.add(file);
        }
    }

    //  Helper method for generating random date
    private long getRandomTimeBetweenTwoDates(long beginTime, long endTime) {
        long diff = endTime - beginTime + 1;
        return beginTime + (long) (Math.random() * diff);
    }

    public List<File> getFileList() {
        return fileList;
    }
}
