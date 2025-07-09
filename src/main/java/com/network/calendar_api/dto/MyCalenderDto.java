package com.network.calendar_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
public class MyCalenderDto {
    private Integer month;
    private Integer day;
    private Integer year;

    public MyCalenderDto(Integer month, Integer day, Integer year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
}
