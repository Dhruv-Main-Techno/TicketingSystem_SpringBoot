package com.main.ticketing.event.controller;

import com.main.ticketing.common.dto.CreateEventRequest;
import com.main.ticketing.common.dto.CreateSectionRequest;
import com.main.ticketing.event.domain.Event;
import com.main.ticketing.event.domain.EventSection;
import com.main.ticketing.event.service.EventSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EventController {

    private final EventSectionService eventSectionService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody CreateEventRequest req) {
        return ResponseEntity.ok(eventSectionService.createEvent(req));
    }

    @PostMapping("/sections")
    public ResponseEntity<EventSection> createSection(@Valid @RequestBody CreateSectionRequest req) {
        return ResponseEntity.ok(eventSectionService.createSection(req));
    }
}
