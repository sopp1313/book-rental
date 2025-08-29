package com.network.calendar_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class EventResponseDto {
    private Long id;

    private String title;

    private String description;

    private LocalDate date;

    private String eventType;

    private Long originalEventId;
}
