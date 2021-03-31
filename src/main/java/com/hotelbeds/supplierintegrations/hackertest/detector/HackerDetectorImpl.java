package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.detector.method.DetectionMethod;
import com.hotelbeds.supplierintegrations.hackertest.detector.method.ExcessiveFailedLoginDetectionMethod;
import com.hotelbeds.supplierintegrations.hackertest.domain.ActivityLog;
import com.hotelbeds.supplierintegrations.hackertest.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class HackerDetectorImpl implements HackerDetector {

    private ActivityLogRepository activityLogRepository;
    private List<DetectionMethod> detectionMethods = new ArrayList<>();

    @Autowired
    public HackerDetectorImpl(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
        detectionMethods.add(new ExcessiveFailedLoginDetectionMethod(activityLogRepository));
    }

    @Override
    public String parseLine(String line) {
        String[] lineFields = line.split(",");
        ActivityLog activityLog = new ActivityLog();
        activityLog.setIp(lineFields[0]);
        activityLog.setDate(Timestamp.valueOf(lineFields[1]));
        activityLog.setAction(lineFields[3]);
        activityLog.setUsername(lineFields[4]);
        activityLogRepository.saveAndFlush(activityLog);

        for (DetectionMethod detectionMethod : detectionMethods) {
            if(detectionMethod.detectError(activityLog)) {
                return lineFields[0];
            }
        }

        return null;
    }
}
