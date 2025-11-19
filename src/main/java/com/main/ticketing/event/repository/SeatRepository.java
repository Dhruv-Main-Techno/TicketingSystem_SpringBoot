package com.main.ticketing.event.repository;

import com.main.ticketing.event.domain.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findBySectionIdAndSeatLabel(Long sectionId, String seatLabel);

    List<Seat> findBySectionId(Long sectionId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Seat s where s.sectionId = :sectionId and s.seatLabel in :labels")
    List<Seat> findBySectionIdAndSeatLabelForUpdate(@Param("sectionId") Long sectionId, @Param("labels") List<String> labels);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Seat s where s.id in :ids")
    List<Seat> findByIdsForUpdate(@Param("ids") List<Long> ids);
}
