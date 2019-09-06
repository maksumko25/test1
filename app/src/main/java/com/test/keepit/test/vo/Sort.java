/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.vo;

import android.content.Context;

import com.test.keepit.test.util.SortDirection;
import com.test.keepit.test.util.SortType;
import com.test.keepit.test.util.Util;

public class Sort {
    private SortDirection direction;
    private SortType type;

    public Sort(SortDirection direction, SortType type) {
        this.direction = direction;
        this.type = type;
    }

    public SortDirection getDirection() {
        return direction;
    }

    public SortType getType() {
        return type;
    }

    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }

    public String getName(Context context) {
        return Util.getStringFromResources(context, "sort_"+type.ordinal(), "");
    }
}
