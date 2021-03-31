package com.hotelbeds.supplierintegrations.hackertest.detector.method;

import com.hotelbeds.supplierintegrations.hackertest.domain.ActivityLog;

public interface DetectionMethod {
    public boolean detectError(ActivityLog activityLog);
}
