package com.network.calendar_api.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MyCalenderDto {
    private Integer month;
    private Integer day;
    private Integer year;
    private List<EventResponseDto> publicEvents;
    private List<EventResponseDto> personalEvents;

    public MyCalenderDto(Integer month, Integer day, Integer year, List<EventResponseDto> publicEvents, List<EventResponseDto> personalEvents) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.publicEvents = publicEvents != null ? publicEvents : List.of();
        this.personalEvents = personalEvents != null ? personalEvents : List.of();
    }
}
