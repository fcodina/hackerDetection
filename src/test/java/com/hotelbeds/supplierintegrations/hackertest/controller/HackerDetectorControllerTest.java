package com.hotelbeds.supplierintegrations.hackertest.controller;

import com.hotelbeds.supplierintegrations.hackertest.HackerTestApplication;
import com.hotelbeds.supplierintegrations.hackertest.TestUtils;
import com.hotelbeds.supplierintegrations.hackertest.domain.LogAction;
import com.hotelbeds.supplierintegrations.hackertest.repository.ActivityLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = HackerTestApplication.class)
public class HackerDetectorControllerTest {

    @Autowired
    private MockMvc restHackerTestMockMvc;

    @Autowired
    ActivityLogRepository activityLogRepository;

    @BeforeEach
    public void initializeTest() {
        activityLogRepository.deleteAll();
    }

    @Test
    public void testParseLine() throws Exception {
        String line = "80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith";

        restHackerTestMockMvc
                .perform(get("/api/parse-line?line=" + line)).andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testParseLineError() throws Exception {
        Instant date = Instant.now();
        String line1 = TestUtils.getLine(date.minus(5, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line2 = TestUtils.getLine(date.minus(4, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line3 = TestUtils.getLine(date.minus(3, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line4 = TestUtils.getLine(date.minus(2, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);
        String line5 = TestUtils.getLine(date.minus(0, ChronoUnit.MINUTES), LogAction.SIGNIN_FAILURE);

        restHackerTestMockMvc
                .perform(get("/api/parse-line?line=" + line1)).andExpect(status().isOk())
                .andExpect(content().string(""));

        restHackerTestMockMvc
                .perform(get("/api/parse-line?line=" + line2)).andExpect(status().isOk())
                .andExpect(content().string(""));

        restHackerTestMockMvc
                .perform(get("/api/parse-line?line=" + line3)).andExpect(status().isOk())
                .andExpect(content().string(""));

        restHackerTestMockMvc
                .perform(get("/api/parse-line?line=" + line4)).andExpect(status().isOk())
                .andExpect(content().string(""));

        restHackerTestMockMvc
                .perform(get("/api/parse-line?line=" + line5)).andExpect(status().isOk())
                .andExpect(content().string("80.238.9.179"));
    }
}
