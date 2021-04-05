package com.hotelbeds.supplierintegrations.hackertest.controller;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HackerDetectorController {

    private final HackerDetector hackerDetector;

    public HackerDetectorController(HackerDetector hackerDetector) {
        this.hackerDetector = hackerDetector;
    }


    /**
     * Parses one log activity line
     *
     * @param line Access log line
     * @return null if the line is correct or the ip if there is suspicious activity.
     */
    @GetMapping("/parse-line")
    public ResponseEntity<String> parseLine(@RequestParam(value = "line", required = true) String line) {
        String result = hackerDetector.parseLine(line);
        return ResponseEntity.ok(result);
    }
}
