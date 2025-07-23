package com.network.calendar_api.service;
import com.network.calendar_api.dto.EventRequestDto;
import com.network.calendar_api.dto.MyCalenderDto;
import com.network.calendar_api.entity.Event;
import com.network.calendar_api.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyCalenderService {
    private final EventRepository eventRepository;

    public List<MyCalenderDto> getMyCalenders(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = LocalDate.of(year, month, 1);
        LocalDate end = yearMonth.atEndOfMonth();

        List<Event> events = eventRepository.findByDateBetween(localDate, end);
        Map<LocalDate, List<Event>> groupp = events.stream().collect(Collectors.groupingBy(Event::getDate));

        List<MyCalenderDto> result = new ArrayList<>();
        for(int day = 1; day<=end.getDayOfMonth(); day++) {
            LocalDate date = LocalDate.of(year, month, day);
            List<EventRequestDto> eventList = groupp.getOrDefault(date, List.of()).stream()
                    .map(e->new EventRequestDto(e.getTitle(), e.getDescription(), e.getDate()))
                    .collect(Collectors.toList());
            result.add(new MyCalenderDto(year, month, day, eventList));
        }
        return result;
    }
}
