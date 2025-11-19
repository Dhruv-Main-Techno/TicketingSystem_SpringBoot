package com.main.ticketing.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ReservationRequest {
    @NotNull
    private Long eventId;
    @NotNull
    private Long sectionId;
    @NotNull
    private List<String> seatLabels;
    private String userId;
}
