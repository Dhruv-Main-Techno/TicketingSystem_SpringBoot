package com.main.ticketing.reservation.domain;

import com.main.ticketing.event.domain.Seat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "ticket_reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketReservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;
    private Long sectionId;
    private String userId;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime expiresAt;

    @Version
    private Long version;

    @ManyToMany
    @JoinTable(name = "ticket_reservation_seats",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private Set<Seat> seats;
}
