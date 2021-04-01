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
    public void testParseLinesSigninSuccess() {
        Instant date = Instant.now();
        String line1 = getLine(date.minus(5, ChronoUnit.MINUTES), LogAction.SIGNIN_SUCCESS);
        String line2 = getLine(date.minus(4, ChronoUnit.MINUTES), LogAction.SIGNIN_SUCCESS);
        String line3 = getLine(date.minus(3, ChronoUnit.MINUTES), LogAction.SIGNIN_SUCCESS);
        String line4 = getLine(date.minus(2, ChronoUnit.MINUTES), LogAction.SIGNIN_SUCCESS);
        String line5 = getLine(date.minus(0, ChronoUnit.MINUTES), LogAction.SIGNIN_SUCCESS);

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
    public void testParseLinesSigninFailureWithMoreThan5minutes() {
        Instant date = Instant.now();
        String line1 = getLine(date.minus(6, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line2 = getLine(date.minus(4, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line3 = getLine(date.minus(3, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line4 = getLine(date.minus(2, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line5 = getLine(date.minus(0, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);

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
        Instant date = Instant.now();
        String line1 = getLine(date.minus(5, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line2 = getLine(date.minus(4, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line3 = getLine(date.minus(3, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line4 = getLine(date.minus(2, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line5 = getLine(date.minus(0, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);

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

    private String getLine(Instant date, LogAction logAction) {
        return new StringBuilder("80.238.9.179,")
                .append(date.toEpochMilli())
                .append(",")
                .append(logAction.name())
                .append(",Will.Smith").toString();
    }
}
