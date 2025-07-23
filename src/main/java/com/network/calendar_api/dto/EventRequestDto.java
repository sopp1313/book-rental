package com.network.calendar_api.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EventRequestDto {
    private String title;
    private String description;
    private String date;

    public EventRequestDto(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }
}
