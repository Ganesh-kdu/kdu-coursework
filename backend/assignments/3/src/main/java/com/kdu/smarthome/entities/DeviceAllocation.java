package com.kdu.smarthome.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "allocation")
/**
 * Device allocation entity to add a device to house, References Room entity and House entity
 */
public class DeviceAllocation {
    @Id
    @Column(name = "kickston_id")
    private String kickstonId;

    @OneToOne
    @JoinColumn(name = "house")
    private House house;

    @OneToOne
    @JoinColumn(name = "room")
    private Room room;
}
