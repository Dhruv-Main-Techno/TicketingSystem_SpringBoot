package com.main.ticketing.event.controller;

import com.main.ticketing.event.domain.Seat;
import com.main.ticketing.event.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events/sections")
@RequiredArgsConstructor
public class SectionSeatController {

    private final SeatRepository seatRepository;

    @GetMapping("/{sectionId}/seats")
    public ResponseEntity<List<Seat>> getSeats(@PathVariable Long sectionId) {
        var seats = seatRepository.findBySectionId(sectionId);
        return ResponseEntity.ok(seats);
    }
}
