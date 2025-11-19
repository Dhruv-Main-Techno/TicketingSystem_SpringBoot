package com.main.ticketing.event.service;

import com.main.ticketing.common.dto.CreateEventRequest;
import com.main.ticketing.common.dto.CreateSectionRequest;
import com.main.ticketing.event.domain.Event;
import com.main.ticketing.event.domain.EventSection;
import com.main.ticketing.event.events.SectionCreatedEvent;
import com.main.ticketing.event.repository.EventRepository;
import com.main.ticketing.event.repository.EventSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventSectionService {

    private final EventRepository eventRepository;
    private final EventSectionRepository sectionRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Event createEvent(CreateEventRequest req) {
        Event e = new Event();
        e.setName(req.getName());
        e.setDescription(req.getDescription());
        return eventRepository.save(e);
    }

    @Transactional
    public EventSection createSection(CreateSectionRequest req) {
        EventSection s = new EventSection();
        s.setEventId(req.getEventId());
        s.setName(req.getName());
        s.setTotalSeats(req.getTotalSeats());
        s.setPrice(req.getPrice());
        EventSection saved = sectionRepository.save(s);

        publisher.publishEvent(new SectionCreatedEvent(saved.getId(), saved.getEventId(), saved.getName(), saved.getTotalSeats()));
        return saved;
    }
}
