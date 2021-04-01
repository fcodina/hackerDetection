package com.hotelbeds.supplierintegrations.hackertest.repository;

import com.hotelbeds.supplierintegrations.hackertest.domain.ActivityLog;
import com.hotelbeds.supplierintegrations.hackertest.domain.LogAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    long countByActionAndDateGreaterThanEqual(LogAction action, Timestamp date);
}
