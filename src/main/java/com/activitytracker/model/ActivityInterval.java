package com.activitytracker.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_intervals")
public class ActivityInterval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int startTime;

    @Column(nullable = false)
    private int endTime;

    @Column(nullable = false)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStartSeconds() {
        return startTime;
    }

    public void setStartSeconds(int start) {
        this.startTime = start;
    }

    public int getEndSeconds() {
        return endTime;
    }

    public void setEndSeconds(int end) {
        this.endTime = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}