/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.util.comparator;

import androidx.annotation.NonNull;

import com.test.keepit.test.util.SortDirection;
import com.test.keepit.test.util.Util;
import com.test.keepit.test.vo.File;
import com.test.keepit.test.vo.Sort;

import java.util.Comparator;

public class FileComparator implements Comparator<File> {

    private Sort sort;

    public FileComparator(Sort sort) {
        this.sort = sort;
    }

    @Override
    public int compare(File obj1, File obj2) {
        int testEntries = nullSafeComparator(obj1, obj2);
        if (testEntries != 2) return testEntries;

        return compareFiles(obj1, obj2);
    }

    private int compareFiles(@NonNull File obj1, @NonNull File obj2) {
        int result = 0;

        switch (sort.getType()) {
            case NAME:
                result = extractInt(obj1.getName()) - extractInt(obj2.getName());
                break;

            case SIZE:
                result = obj1.getFileSize() > obj2.getFileSize() ? 1 : -1;
                break;

            case DATE:
                result = obj1.getDateTimeStamp() > obj2.getDateTimeStamp() ? 1 : -1;
                break;

            default:
                break;
        }

        if (sort.getDirection() == SortDirection.DESC) result = Util.negateValue(result);

        return result;
    }

    private int extractInt(String s) {
        String num = s.replaceAll("\\D", "");
        // return 0 if no digits found
        return num.isEmpty() ? 0 : Integer.parseInt(num);
    }

    private int nullSafeComparator(final Object one, final Object two) {
        if (one == null && two == null) {
            return 0;
        }

        if (one == null) {
            return 1;
        }

        if (two == null) {
            return -1;
        }

        return 2;
    }
}
