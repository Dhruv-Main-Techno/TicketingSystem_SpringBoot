package com.main.ticketing.reservation.controller;

import com.main.ticketing.common.dto.ReservationRequest;
import com.main.ticketing.common.dto.ReservationResponse;
import com.main.ticketing.reservation.domain.TicketReservation;
import com.main.ticketing.reservation.service.TicketReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class TicketReservationController {


    private final TicketReservationService reservationService;

    @PostMapping("/hold")
    public ResponseEntity<ReservationResponse> hold(@Valid @RequestBody ReservationRequest req) {
        TicketReservation res = reservationService.holdSeatsPessimistic(req);
        return ResponseEntity.ok(new ReservationResponse(res.getId(), res.getStatus().name(), "Seats held until: " + res.getExpiresAt()));
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<ReservationResponse> confirm(@PathVariable Long id) {
        reservationService.confirmReservation(id);
        return ResponseEntity.ok(new ReservationResponse(id, "CONFIRMED", "Reservation confirmed"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long id) {
        TicketReservation res = reservationService.getReservation(id);
        return ResponseEntity.ok(new ReservationResponse(
                res.getId(),
                res.getStatus().name(),
                "Status: " + res.getStatus()
        ));
    }



}
