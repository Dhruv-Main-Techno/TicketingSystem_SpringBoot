package com.main.ticketing.reservation.service;

import com.main.ticketing.common.dto.ReservationRequest;
import com.main.ticketing.event.domain.Seat;
import com.main.ticketing.event.domain.SeatStatus;
import com.main.ticketing.event.repository.SeatRepository;
import com.main.ticketing.reservation.domain.ReservationStatus;
import com.main.ticketing.reservation.domain.TicketReservation;
import com.main.ticketing.reservation.repository.TicketReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketReservationService {

    private final SeatRepository seatRepository;
    private final TicketReservationRepository reservationRepository;

    @Value("${ticket.hold.minutes:15}")
    private int holdMinutes;

    /**
     * Hold seats using PESSIMISTIC write lock on the selected seats.
     */
    @Transactional
    public TicketReservation holdSeatsPessimistic(ReservationRequest req) {
        List<String> labels = req.getSeatLabels();
        List<Seat> seats = seatRepository.findBySectionIdAndSeatLabelForUpdate(req.getSectionId(), labels);

        if (seats.size() != labels.size()) {
            Set<String> found = seats.stream().map(Seat::getSeatLabel).collect(Collectors.toSet());
            List<String> missing = labels.stream().filter(l -> !found.contains(l)).collect(Collectors.toList());
            throw new EntityNotFoundException("Seats not found: " + missing);
        }

        for (Seat s : seats) {
            if (s.getStatus() != SeatStatus.AVAILABLE) {
                throw new IllegalStateException("Seat not available: " + s.getSeatLabel());
            }
        }

        seats.forEach(s -> s.setStatus(SeatStatus.HELD));
        seatRepository.saveAll(seats);

        TicketReservation res = new TicketReservation();
        res.setEventId(req.getEventId());
        res.setSectionId(req.getSectionId());
        res.setUserId(req.getUserId());
        res.setStatus(ReservationStatus.HELD);
        res.setCreatedAt(LocalDateTime.now());
        res.setExpiresAt(LocalDateTime.now().plusMinutes(holdMinutes));
        res.setSeats(new HashSet<>(seats));
        return reservationRepository.save(res);
    }

    @Transactional
    public void confirmReservation(Long reservationId) {
        TicketReservation res = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found: " + reservationId));
        if (res.getStatus() != ReservationStatus.HELD) {
            throw new IllegalStateException("Cannot confirm reservation status=" + res.getStatus());
        }

        List<Long> seatIds = res.getSeats().stream().map(Seat::getId).collect(Collectors.toList());
        List<Seat> seats = seatRepository.findByIdsForUpdate(seatIds);

        for (Seat s : seats) {
            if (s.getStatus() != SeatStatus.HELD) {
                throw new IllegalStateException("Seat not held anymore: " + s.getSeatLabel());
            }
            s.setStatus(SeatStatus.BOOKED);
        }
        seatRepository.saveAll(seats);

        res.setStatus(ReservationStatus.CONFIRMED);
        reservationRepository.save(res);
    }

    @Transactional
    public void expireReservation(Long reservationId) {
        TicketReservation res = reservationRepository.findById(reservationId).orElse(null);
        if (res == null) return;
        if (res.getStatus() != ReservationStatus.HELD) return;

        List<Long> seatIds = res.getSeats().stream().map(Seat::getId).collect(Collectors.toList());
        if (!seatIds.isEmpty()) {
            List<Seat> seats = seatRepository.findByIdsForUpdate(seatIds);
            seats.forEach(s -> s.setStatus(SeatStatus.AVAILABLE));
            seatRepository.saveAll(seats);
        }

        res.setStatus(ReservationStatus.EXPIRED);
        reservationRepository.save(res);
    }

    public List<TicketReservation> findExpiredHolds(LocalDateTime before) {
        return reservationRepository.findByStatusAndExpiresAtBefore(ReservationStatus.HELD, before);
    }
}
