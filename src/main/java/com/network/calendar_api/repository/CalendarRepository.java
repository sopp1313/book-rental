package com.network.calendar_api.repository;

import com.network.calendar_api.entity.MyCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 잘못된 타입 참조 수정: CalendarRepository -> MyCalendar 엔티티로 변경
@Repository
public interface CalendarRepository extends JpaRepository<MyCalendar, Integer> {

}
