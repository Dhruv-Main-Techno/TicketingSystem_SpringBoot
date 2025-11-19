package com.main.ticketing.reservation.repository;

import com.main.ticketing.reservation.domain.TicketReservation;
import com.main.ticketing.reservation.domain.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketReservationRepository extends JpaRepository<TicketReservation, Long> {
    List<TicketReservation> findByStatusAndExpiresAtBefore(ReservationStatus status, LocalDateTime time);
}
