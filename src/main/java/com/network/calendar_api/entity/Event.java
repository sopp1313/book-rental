package com.network.calendar_api.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private LocalDate date;

}
