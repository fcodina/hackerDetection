package com.hotelbeds.supplierintegrations.hackertest.service;

import com.hotelbeds.supplierintegrations.hackertest.domain.ActivityLog;
import com.hotelbeds.supplierintegrations.hackertest.domain.LogAction;
import com.hotelbeds.supplierintegrations.hackertest.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;


@Service
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    @Autowired
    public ActivityLogService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    public ActivityLog save(ActivityLog activityLog) {
        return activityLogRepository.save(activityLog);
    }

    @Transactional(readOnly = true)
    public Page<ActivityLog> findAll(final Pageable pageable) {
        return this.activityLogRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<ActivityLog> findOne(final Long id) {
        return this.activityLogRepository.findById(id);
    }

    public void delete(final Long id) {
        this.activityLogRepository.deleteById(id);
    }

    public long countByActionAndDateGreaterThanEqual(LogAction action, Timestamp date) {
        return activityLogRepository.countByActionAndDateGreaterThanEqual(action, date);
    }
}
