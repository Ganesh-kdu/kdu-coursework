package com.kdu.smarthome.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Device entity containing device details
 */
@Table(name = "inventory")
public class Device {
    @Id
    @Column(name = "kickston_id")
    private String kickstonId;

    @Column(name = "username")
    private String deviceUsername;

    @Column(name = "password")
    private String devicePassword;

    @Column(name = "manufacture_date_time")
    private LocalDateTime manufactureDateTime;

    @Column(name = "manufacture_factory_place")
    private String manufactureFactoryPlace;
}
