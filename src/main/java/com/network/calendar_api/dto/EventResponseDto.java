package com.network.calendar_api.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EventResponseDto {
    private int id;

    private String title;

    private String description;

    private LocalDate date;

    private String eventType;

    private Long originalEventId;
}
