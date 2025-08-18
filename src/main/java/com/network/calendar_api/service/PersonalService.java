package com.network.calendar_api.service;

import com.network.calendar_api.dto.EventRequestDto;
import com.network.calendar_api.entity.Event;
import com.network.calendar_api.repository.EventRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class PersonalService {
    private final EventRepository eventRepository;

    public PersonalService( EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Secured("ROLE_USER")
    public long create(EventRequestDto req) {
        Event e = Event.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .date(req.getDate())
                .build();

        return eventRepository.save(e).getId();
    }

    @Secured("ROLE_USER")
    public void delete(Long id) {
        var events = eventRepository.findById(id).orElseThrow();
        eventRepository.delete(events);
    }

    @Secured("ROLE_USER")
    public void setting(Long id, EventRequestDto req) {
        var event = eventRepository.findById(id).orElseThrow();
        event.setTitle(req.getTitle());
        event.setDescription(req.getDescription());
        event.setDate(req.getDate());
    }
}
