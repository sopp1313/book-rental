package com.network.calendar_api.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private String date;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = " membetLoginID")
    private Member member;

    public Event(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.eventType = EventType.PUBLIC;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
