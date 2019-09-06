/*
 * Copyright (c) 2019. Created by Hameliak Maksym
 */

package com.test.keepit.test.util;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

public final class Util {
    public static String formatFileSize(long size) {
        String hrSize;

        double b = size;
        double k = size / 1024.0;
        double m = ((size / 1024.0) / 1024.0);
        double g = (((size / 1024.0) / 1024.0) / 1024.0);
        double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if (t > 1) {
            hrSize = dec.format(t).concat(" TB");
        } else if (g > 1) {
            hrSize = dec.format(g).concat(" GB");
        } else if (m > 1) {
            hrSize = dec.format(m).concat(" MB");
        } else if (k > 1) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }

        return hrSize;
    }

    public static int negateValue(int value) {
        value *= -1;
        return value;
    }

    public static String getStringFromResources(@NonNull Context context, String string, String defaultValue) {
        try {
            Resources resources = context.getResources();
            int resId = resources.getIdentifier(string, "string", context.getPackageName());
            return resources.getString(resId);
        } catch (Resources.NotFoundException ignored){
            return defaultValue;
        }
    }
}
