package com.hotelbeds.supplierintegrations.hackertest.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeCalculatorTest {

    @Test
    public void testGetMinutesBetween() {
        String time1 = "Thu, 21 Dec 2000 16:01:07 +0200";
        String time2 = "Thu, 21 Dec 2000 16:03:07 +0200";

        long result = TimeCalculator.getMinutesBetween(time1, time2);
        Assertions.assertEquals(2l, result);

        // Test rounded down
        time2 = "Thu, 21 Dec 2000 16:03:08 +0200";
        result = TimeCalculator.getMinutesBetween(time1, time2);
        Assertions.assertEquals(2l, result);

        time2 = "Thu, 21 Dec 2000 16:03:06 +0200";
        result = TimeCalculator.getMinutesBetween(time1, time2);
        Assertions.assertEquals(1l, result);

        // Test different timezones
        time1 = "Thu, 21 Dec 2000 16:01:07 +0200";
        time2 = "Thu, 21 Dec 2000 16:03:07 +0100";
        result = TimeCalculator.getMinutesBetween(time1, time2);
        Assertions.assertEquals(62l, result);

        time1 = "Thu, 21 Dec 2000 18:01:07 +0400";
        time2 = "Thu, 21 Dec 2000 16:03:07 +0200";
        result = TimeCalculator.getMinutesBetween(time1, time2);
        Assertions.assertEquals(2l, result);
    }
}
