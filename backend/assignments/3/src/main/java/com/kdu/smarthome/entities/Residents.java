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
/**
 * Residents entity to track users added to each house and respective roles.
 * Roles are store for each house the user is added to.
 * @see CompositeKey
 */
public class Residents {
    @EmbeddedId
    private CompositeKey id;

    @Column(name = "is_admin")
    private Boolean admin;
}
