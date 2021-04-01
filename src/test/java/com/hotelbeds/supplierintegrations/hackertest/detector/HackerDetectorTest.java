package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HackerDetectorTest {

    @Autowired
    private HackerDetectorImpl hackerDetectorImpl;

    @Test
    public void testParseOneLineSuccess() {
        String line = "80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith";
        String result = hackerDetectorImpl.parseLine(line);
        Assertions.assertNull(result);
    }

}
