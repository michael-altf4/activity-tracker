package com.activitytracker.rest.controller;


import com.activitytracker.dto.CreateIntervalRequest;
import com.activitytracker.model.ActivityInterval;
import com.activitytracker.repository.ActivityIntervalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/intervals")
public class IntervalController {

    @Autowired
    private ActivityIntervalRepository repository;

    @PostMapping
    public ResponseEntity<?> createInterval(@Valid @RequestBody CreateIntervalRequest request) {

        if (request.endSeconds() <= request.startSeconds()) {
            return ResponseEntity.badRequest().body("Конец должен быть позже начала");
        }

        if (request.startSeconds() < 0 || request.startSeconds() > 86399 ||
                request.endSeconds() < 0 || request.endSeconds() > 86399) {
            return ResponseEntity.badRequest().body("Время должно быть в диапазоне 0–86399 секунд");
        }

        if (!request.type().equals("WORK") && !request.type().equals("BREAK")) {
            return ResponseEntity.badRequest().body("Тип должен быть WORK или BREAK");
        }

        List<ActivityInterval> allIntervals = repository.findAll();

        int newStart = request.startSeconds();
        int newEnd = request.endSeconds();

        for (ActivityInterval existingInterval : allIntervals) {
            int existingStart = existingInterval.getStartSeconds();
            int existingEnd = existingInterval.getEndSeconds();

            if (Math.max(newStart, existingStart) < Math.min(newEnd, existingEnd)) {
                return ResponseEntity.badRequest().body(
                        String.format("Пересечение с существующим интервалом: " +
                                        "%s — %s (%s)",
                                secondsToTime(existingStart),
                                secondsToTime(existingEnd),
                                existingInterval.getType().equals("WORK") ? "Работа" : "Перерыв")

                );
            }
        }

        ActivityInterval interval = new ActivityInterval();
        interval.setStartSeconds(request.startSeconds());
        interval.setEndSeconds(request.endSeconds());
        interval.setType(request.type());

        ActivityInterval saved = repository.save(interval);
        return ResponseEntity.ok(saved);
    }
    private String secondsToTime(int seconds) {
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        return String.format("%02d:%02d", h, m);
    }

    @GetMapping
    public List<ActivityInterval> getIntervals() {
        return repository.findAll();
    }
}