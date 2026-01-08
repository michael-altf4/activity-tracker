package com.activitytracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateIntervalRequest(
        @NotNull
        Integer startSeconds,

        @NotNull
        Integer endSeconds,

        @NotBlank
        String type
) {}