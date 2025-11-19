package com.main.ticketing.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSectionRequest {
    @NotNull
    private Long eventId;
    @NotNull
    private String name;
    @NotNull
    private Integer totalSeats;
    @NotNull
    private Double price;
}
