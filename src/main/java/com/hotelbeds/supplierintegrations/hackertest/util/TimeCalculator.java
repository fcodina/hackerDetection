package com.hotelbeds.supplierintegrations.hackertest.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeCalculator {

    /**
     * Return minutes between two dates in RFC-822 / RFC-1123.
     *
     * @param time1 String date in RFC-822 / RFC-1123 format.
     * @param time2 String date in RFC-822 / RFC-1123 format.
     * @return long
     */
    public static long getMinutesBetween(String time1, String time2) {
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

        ZonedDateTime zonedDateTime1 = ZonedDateTime.parse(time1, formatter);
        ZonedDateTime zonedDateTime2 = ZonedDateTime.parse(time2, formatter);

        return ChronoUnit.MINUTES.between(zonedDateTime1, zonedDateTime2);
    }
}
