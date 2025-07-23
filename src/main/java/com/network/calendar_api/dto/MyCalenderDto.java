package com.network.calendar_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Getter
public class MyCalenderDto {
    private Integer month;
    private Integer day;
    private Integer year;
    private List<EventRequestDto> events;

    public MyCalenderDto(Integer month, Integer day, Integer year, List<EventRequestDto> events) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.events = events;
    }
}
