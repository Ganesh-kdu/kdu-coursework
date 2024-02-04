package com.kdu.smarthome.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "device_ownership")
public class DeviceRegistration {
    @Id
    @Column(name = "kickston_id")
    private String kickstonId;

    @ManyToOne
    @JoinColumn(name = "registeredUser")
    private User user;
}
