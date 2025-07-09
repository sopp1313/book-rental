package com.network.calendar_api.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EventRequestDto {
    private String title;

    private String description;

    private LocalDate date;

    private Integer startYear;
    private Integer endYear;
    private Integer startMonth;
    private Integer endMonth;
    private Integer startDay;
    private Integer endDay;

    public EventRequestDto(String title, String description, LocalDate date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

}
