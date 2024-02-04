package com.kdu.smarthome.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "residents")
public class Residents {
    @EmbeddedId
    private CompositeKey id;

    @Column(name = "is_admin")
    private Boolean admin;
}
