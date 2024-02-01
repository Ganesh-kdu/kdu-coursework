package com.example.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ShiftTypeDto {
    private String name;
    private String description;
    private boolean active;
    private String timeZone;
    private String tenantId;
}