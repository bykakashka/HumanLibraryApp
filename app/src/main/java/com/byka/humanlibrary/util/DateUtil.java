package com.byka.humanlibrary.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String PATTERN = "yyyy-MM-dd_HH:mm:ss";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
    private DateUtil() {
        // do nothing as it's a static class
    }

    public static String convert(Date date) {
        return dateFormat.format(date);
    }
}
