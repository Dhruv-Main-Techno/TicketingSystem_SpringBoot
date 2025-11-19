package com.main.ticketing.event.repository;

import com.main.ticketing.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> { }
