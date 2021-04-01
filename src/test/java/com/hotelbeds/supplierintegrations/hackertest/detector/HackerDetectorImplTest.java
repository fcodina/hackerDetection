package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.domain.LogAction;
import com.hotelbeds.supplierintegrations.hackertest.repository.ActivityLogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@SpringBootTest
public class HackerDetectorImplTest {

    @Autowired
    private HackerDetectorImpl hackerDetectorImpl;

    @Autowired
    ActivityLogRepository activityLogRepository;

    @BeforeEach
    public void initializeTest() {
        activityLogRepository.deleteAll();
    }

    @Test
    public void testParseOneLineSigninSuccess() {
        String line = "80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith";
        String result = hackerDetectorImpl.parseLine(line);
        Assertions.assertNull(result);
    }

    @Test
    public void testParseLinesSigninFailureWithMoreThan5minutes() {
        String line1 = getLine(7, LogAction.SIGNIN_FAILURE);
        String line2 = getLine(5, LogAction.SIGNIN_FAILURE);
        String line3 = getLine(4, LogAction.SIGNIN_FAILURE);
        String line4 = getLine(3, LogAction.SIGNIN_FAILURE);
        String line5 = getLine(2, LogAction.SIGNIN_FAILURE);

        String result = hackerDetectorImpl.parseLine(line1);
        Assertions.assertNull(result);
        result = hackerDetectorImpl.parseLine(line2);
        Assertions.assertNull(result);
        result = hackerDetectorImpl.parseLine(line3);
        Assertions.assertNull(result);
        result = hackerDetectorImpl.parseLine(line4);
        Assertions.assertNull(result);
        result = hackerDetectorImpl.parseLine(line5);
        Assertions.assertNull(result);
    }

    @Test
    public void testParseLinesSigninFailureError() {
        String line1 = getLine(4, LogAction.SIGNIN_FAILURE);
        String line2 = getLine(4, LogAction.SIGNIN_FAILURE);
        String line3 = getLine(3, LogAction.SIGNIN_FAILURE);
        String line4 = getLine(2, LogAction.SIGNIN_FAILURE);
        String line5 = getLine(1, LogAction.SIGNIN_FAILURE);

        String result = hackerDetectorImpl.parseLine(line1);
        Assertions.assertNull(result);
        result = hackerDetectorImpl.parseLine(line2);
        Assertions.assertNull(result);
        result = hackerDetectorImpl.parseLine(line3);
        Assertions.assertNull(result);
        result = hackerDetectorImpl.parseLine(line4);
        Assertions.assertNull(result);
        result = hackerDetectorImpl.parseLine(line5);
        Assertions.assertEquals("80.238.9.179", result);
    }

    private String getLine(int minutesBefore, LogAction logAction) {
        return new StringBuilder("80.238.9.179,")
                .append(Instant.now().minus(minutesBefore, ChronoUnit.MINUTES).toEpochMilli())
                .append(",")
                .append(logAction.name())
                .append(",Will.Smith").toString();
    }
}
