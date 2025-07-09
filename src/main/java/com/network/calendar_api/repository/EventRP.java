package com.network.calendar_api.repository;
import com.network.calendar_api.entity.Event;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRP extends JpaRepository<Event, Integer> {
List<Event> findByDate(int year, int month, int day);
List<Event> findByTitle(String title);
List<Event> findByDescription(String description);
List<Event> findByDateBetween(LocalDate start, LocalDate end);
}
