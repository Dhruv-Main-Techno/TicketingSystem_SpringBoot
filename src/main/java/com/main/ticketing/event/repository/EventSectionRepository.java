package com.main.ticketing.event.repository;

import com.main.ticketing.event.domain.EventSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventSectionRepository extends JpaRepository<EventSection, Long> { }
