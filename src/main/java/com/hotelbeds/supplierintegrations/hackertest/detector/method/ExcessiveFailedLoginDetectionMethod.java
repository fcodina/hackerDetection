package com.hotelbeds.supplierintegrations.hackertest.detector.method;

import com.hotelbeds.supplierintegrations.hackertest.domain.ActivityLog;
import com.hotelbeds.supplierintegrations.hackertest.repository.ActivityLogRepository;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class ExcessiveFailedLoginDetectionMethod implements DetectionMethod {

    private static final Long MILLIS_BEFORE_LAST_LOGIN = TimeUnit.MINUTES.toMillis(5);
    private static final long NUMBER_FAILED_LOGIN = 5L;

    private ActivityLogRepository activityLogRepository;

    public ExcessiveFailedLoginDetectionMethod(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @Override
    public boolean detectError(ActivityLog activityLog) {
        Timestamp timeBefore = new Timestamp(activityLog.getDate().getTime() - MILLIS_BEFORE_LAST_LOGIN);
        if(activityLogRepository.countByDateBefore(timeBefore) > NUMBER_FAILED_LOGIN) {
            return true;
        }
        return false;
    }
}
