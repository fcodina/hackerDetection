package com.hotelbeds.supplierintegrations.hackertest.detector.method;

import com.hotelbeds.supplierintegrations.hackertest.domain.ActivityLog;
import com.hotelbeds.supplierintegrations.hackertest.domain.LogAction;
import com.hotelbeds.supplierintegrations.hackertest.service.ActivityLogService;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class ExcessiveFailedLoginDetectionMethod implements DetectionMethod {

    private static final Long MILLIS_BEFORE_LAST_LOGIN = TimeUnit.MINUTES.toMillis(5);
    private static final long NUMBER_FAILED_LOGIN = 5L;

    private final ActivityLogService activityLogService;

    public ExcessiveFailedLoginDetectionMethod(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @Override
    public boolean detectError(ActivityLog activityLog) {
        Timestamp timeBefore = new Timestamp(activityLog.getDate().getTime() - MILLIS_BEFORE_LAST_LOGIN);
        if(activityLogService.countByActionAndDateBefore(LogAction.SIGNIN_FAILURE, timeBefore) > NUMBER_FAILED_LOGIN) {
            return true;
        }
        return false;
    }
}
