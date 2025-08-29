package com.network.calendar_api.service;

import com.network.calendar_api.dto.EventResponseDto;
import com.network.calendar_api.dto.MyCalenderDto;
import com.network.calendar_api.entity.Event;
import com.network.calendar_api.entity.EventType;
import com.network.calendar_api.entity.Member;
import com.network.calendar_api.repository.EventRepository;
import com.network.calendar_api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyCalenderService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;

    public List<MyCalenderDto> getPublicCalender(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end   = ym.atEndOfMonth();

        List<Event> publics = eventRepository
                .findByEventTypeAndDateBetween(EventType.PUBLIC, start, end);

        Map<LocalDate, List<Event>> pubByDate = publics.stream()
                .collect(Collectors.groupingBy(Event::getDate));

        List<MyCalenderDto> result = new ArrayList<>();
        for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
            List<EventResponseDto> pubDtos = pubByDate.getOrDefault(d, List.of()).stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());

            result.add(new MyCalenderDto(
                    d.getMonthValue(), d.getDayOfMonth(), d.getYear(),
                    pubDtos, List.of()
            ));
        }
        return result;
    }

    public List<MyCalenderDto> getMyCalenders(String memberId, int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end   = ym.atEndOfMonth();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다: " + memberId));

        List<Event> publics   = eventRepository.findByEventTypeAndDateBetween(EventType.PUBLIC, start, end);
        List<Event> personals = eventRepository.findByMemberAndEventTypeAndDateBetween(member, EventType.PERSONAL, start, end);

        Map<LocalDate, List<Event>> pubByDate = publics.stream()
                .collect(Collectors.groupingBy(Event::getDate));
        Map<LocalDate, List<Event>> perByDate = personals.stream()
                .collect(Collectors.groupingBy(Event::getDate));

        List<MyCalenderDto> result = new ArrayList<>();
        for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
            List<EventResponseDto> pubDtos = pubByDate.getOrDefault(d, List.of()).stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());

            List<EventResponseDto> perDtos = perByDate.getOrDefault(d, List.of()).stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());

            result.add(new MyCalenderDto(
                    d.getMonthValue(), d.getDayOfMonth(), d.getYear(),
                    pubDtos, perDtos
            ));
        }
        return result;
    }

    private EventResponseDto toResponse(Event e) {
        return EventResponseDto.builder()
                .id(e.getId())
                .title(e.getTitle())
                .description(e.getDescription())
                .date(e.getDate())
                .eventType(e.getEventType().name())
                .build();
    }
}
