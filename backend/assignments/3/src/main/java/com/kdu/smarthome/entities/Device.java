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
@Table(name = "inventory")
public class Device {
    @Id
    @Column(name = "kickston_id")
    private String kickston_id;

    @Column(name = "username")
    private String device_username;

    @Column(name = "password")
    private String device_password;

    @Column(name = "manufacture")
    private LocalDateTime manufacture_date_time;
//    LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55");(format: "yyyy-MM-dd'T'HH:mm:ss")

    @Column(name = "factory")
    private String manufacture_factory_place;
}
