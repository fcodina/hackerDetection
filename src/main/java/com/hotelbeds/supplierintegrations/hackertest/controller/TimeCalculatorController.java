package com.hotelbeds.supplierintegrations.hackertest.controller;

import com.hotelbeds.supplierintegrations.hackertest.util.TimeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DateTimeException;

@RestController
@RequestMapping("/api")
public class TimeCalculatorController {

    private final Logger log = LoggerFactory.getLogger(TimeCalculatorController.class);

    /**
     * Computes the number of minutes between two timestamps.
     *
     * @param time1 Timestamp in RFC 2822 format
     * @param time2 Timestamp in RFC 2822 format
     *
     * @return Long with the number of minutes (rounded down) between two
     * timestamps.
     */
    @GetMapping("/calculate-time")
    public ResponseEntity<Long> getCalculateTime(@RequestParam(value = "time1", required = true) String time1,
                                                 @RequestParam(value = "time2", required = true) String time2) {
        log.debug("REST request to calculate the time between {} and {}", time1, time2);
        try {
            Long result = TimeCalculator.getMinutesBetween(time1, time2);
            return ResponseEntity.ok().body(result);
        } catch (DateTimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
