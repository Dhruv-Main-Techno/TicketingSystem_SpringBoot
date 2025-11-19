package com.main.ticketing.event.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_sections")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    private String name;

    private Integer totalSeats;

    private Double price;

    private LocalDateTime createdAt = LocalDateTime.now();
}
