package com.network.calendar_api.repository;
import com.network.calendar_api.entity.Event;
import com.network.calendar_api.entity.EventType;
import com.network.calendar_api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
List<Event> findByDateBetween(LocalDate start, LocalDate end);
List<Event> findByEventType(EventType eventType);
List<Event> findByMemberAndEventType(Member member, EventType eventType);
List<Event> findByMember(Member member);

@Query("SELECT e FROM Event e WHERE e.eventType ='PUBLIC' AND e.date LIKE :yearMonth% ")
List<Event> findPublicEventByYearMonth(@Param("yearMonth") Integer yearMonth);

@Query("SELECT e FROM Event e WHERE e.eventType ='PERSONAL' AND e.date LIKE :yearMonth% ")
List<Event> findPersonalEventByMembetAndYearMonth(@Param("membet") Member member,@Param("yearMonth") Integer yearMonth);
}
