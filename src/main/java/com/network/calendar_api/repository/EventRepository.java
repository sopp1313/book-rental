package com.network.calendar_api.repository;
import com.network.calendar_api.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
List<Event> findByDateBetween(LocalDate start, LocalDate end);

}
