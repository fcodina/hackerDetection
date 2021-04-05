package com.hotelbeds.supplierintegrations.hackertest.controller;

import com.hotelbeds.supplierintegrations.hackertest.HackerTestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(classes = HackerTestApplication.class)
public class HackerDetectorControllerTest {

    @Autowired
    private MockMvc restHackerTestMockMvc;

    @Test
    public void testParseLine() {

    }
}
