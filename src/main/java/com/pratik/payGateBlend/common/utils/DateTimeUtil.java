package com.pratik.payGateBlend.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DateTimeUtil {

    public static String getDateInGripsFormat(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public static String getSysTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        return now.toString(); // ISO-8601 format with nanoseconds
    }
}
