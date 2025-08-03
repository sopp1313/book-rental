package com.network.calendar_api.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    @CreatedDate
    private LocalDate time;

    private LocalDate date;

    public Event(String title, String description, LocalDate date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }
}
