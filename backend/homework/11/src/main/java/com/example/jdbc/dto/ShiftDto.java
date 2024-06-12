package com.example.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
@Data
@AllArgsConstructor
public class ShiftDto {
    private String shiftTypeId;
    private String name;
    private String dateStart;
    private String dateEnd;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private String timeZone;
    private String tenantId;
}