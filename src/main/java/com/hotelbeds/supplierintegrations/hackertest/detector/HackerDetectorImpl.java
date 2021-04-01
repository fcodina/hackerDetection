package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.detector.method.DetectionMethod;
import com.hotelbeds.supplierintegrations.hackertest.detector.method.ExcessiveFailedLoginDetectionMethod;
import com.hotelbeds.supplierintegrations.hackertest.domain.ActivityLog;
import com.hotelbeds.supplierintegrations.hackertest.domain.LogAction;
import com.hotelbeds.supplierintegrations.hackertest.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class HackerDetectorImpl implements HackerDetector {

    private final ActivityLogService activityLogService;
    private List<DetectionMethod> detectionMethods = new ArrayList<>();

    @Autowired
    public HackerDetectorImpl(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
        detectionMethods.add(new ExcessiveFailedLoginDetectionMethod(activityLogService));
    }

    @Override
    public String parseLine(String line) {
        ActivityLog activityLog = createActivityLog(line);
        activityLogService.save(activityLog);

        for (DetectionMethod detectionMethod : detectionMethods) {
            if(detectionMethod.detectError(activityLog)) {
                return activityLog.getIp();
            }
        }
        return null;
    }

    public ActivityLog createActivityLog(String line) {
        String[] lineFields = line.split(",");

        ActivityLog activityLog = new ActivityLog();
        activityLog.setIp(lineFields[0]);

        Timestamp date = Timestamp.from(Instant.ofEpochMilli(Long.valueOf(lineFields[1])));
        activityLog.setDate(date);
        activityLog.setAction(LogAction.valueOf(lineFields[2]));
        activityLog.setUsername(lineFields[3]);
        return activityLog;
    }
}
