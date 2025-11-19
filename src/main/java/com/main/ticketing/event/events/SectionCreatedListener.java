package com.main.ticketing.event.events;

import com.main.ticketing.event.domain.Seat;
import com.main.ticketing.event.domain.SeatStatus;
import com.main.ticketing.event.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SectionCreatedListener {

    private final SeatRepository seatRepository;

    @Transactional
    @EventListener
    public void onSectionCreated(SectionCreatedEvent ev) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= ev.totalSeats(); i++) {
            Seat s = new Seat();
            s.setSectionId(ev.sectionId());
            s.setSeatLabel("S" + i);
            s.setStatus(SeatStatus.AVAILABLE);
            seats.add(s);
        }
        seatRepository.saveAll(seats);
    }
}
