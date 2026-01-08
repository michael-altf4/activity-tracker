package com.activitytracker.repository;

import com.activitytracker.model.ActivityInterval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityIntervalRepository extends JpaRepository<ActivityInterval, Long> {
}