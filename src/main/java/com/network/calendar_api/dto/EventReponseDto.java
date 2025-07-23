package com.network.calendar_api.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EventReponseDto {
    private int id;

    private String title;

    private String description;

    private LocalDate date;
}
