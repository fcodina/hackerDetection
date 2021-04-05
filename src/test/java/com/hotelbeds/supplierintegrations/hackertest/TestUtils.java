package com.hotelbeds.supplierintegrations.hackertest;

import com.hotelbeds.supplierintegrations.hackertest.domain.LogAction;

import java.time.Instant;

public class TestUtils {

    private TestUtils() {
        // Utility class with static methods
    }

    public static String getLine(Instant date, LogAction logAction) {
        return new StringBuilder("80.238.9.179,")
                .append(date.toEpochMilli())
                .append(",")
                .append(logAction.name())
                .append(",Will.Smith").toString();
    }
}
