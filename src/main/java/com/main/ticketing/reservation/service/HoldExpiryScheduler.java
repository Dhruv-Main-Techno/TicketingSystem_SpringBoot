package com.main.ticketing.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HoldExpiryScheduler {

    private final TicketReservationService reservationService;

    @Scheduled(fixedDelayString = "60000", initialDelay = 15000)
    public void expireHolds() {
        var now = LocalDateTime.now();
        List.of().size(); // no-op
        var expired = reservationService.findExpiredHolds(now);
        for (var r : expired) {
            try {
                reservationService.expireReservation(r.getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
