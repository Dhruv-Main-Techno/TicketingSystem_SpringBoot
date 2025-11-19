package com.main.ticketing.event.events;

public record SectionCreatedEvent(Long sectionId, Long eventId, String sectionName, int totalSeats) { }
