package com.hotelbeds.supplierintegrations.hackertest.controller;

import com.hotelbeds.supplierintegrations.hackertest.HackerTestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = HackerTestApplication.class)
public class TimeCalculatorControllerTest {

    @Autowired
    private MockMvc restHackerTestMockMvc;

    @Test
    public void testGetCalculateTime() throws Exception {
        String time1 = "Thu, 21 Dec 2000 16:01:07 +0200";
        String time2 = "Thu, 21 Dec 2000 16:03:07 +0200";

        // Get all the SuperHero
        restHackerTestMockMvc
                .perform(get("/api/calculate-time?time1=" + time1 + "&time2=" + time2))
                    .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    public void testGetCalculateTimeWithDifferentTimeZone() throws Exception {
        String time1 = "Thu, 21 Dec 2000 16:01:07 +0200";
        String time2 = "Thu, 21 Dec 2000 19:03:07 +0500";

        // Get all the SuperHero
        restHackerTestMockMvc
                .perform(get("/api/calculate-time?time1=" + time1 + "&time2=" + time2))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    public void testGetCalculateTimeError() throws Exception {
        String time1 = "Thu, 21 Dec 2000 16:01:07 +0200";
        String time2 = "Thursday, 21 Dec 2000 19:03:07 +0500";

        // Get all the SuperHero
        restHackerTestMockMvc
                .perform(get("/api/calculate-time?time1=" + time1 + "&time2=" + time2))
                .andExpect(status().isBadRequest());
    }
}
