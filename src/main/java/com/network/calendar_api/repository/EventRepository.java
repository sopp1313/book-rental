package com.network.calendar_api.repository;
import com.network.calendar_api.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
List<Event> findByDateBetween(LocalDate start, LocalDate end);
}
