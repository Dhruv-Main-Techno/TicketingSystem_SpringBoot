package com.main.ticketing.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationResponse {
    private Long reservationId;
    private String status;
    private String message;
}
