package dev.omedia.domains;

import dev.omedia.enums.TechnicType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "technics")
public class Technic extends Item {

    @Column(name = "type", nullable = false)
    private TechnicType type;

    @Column(name = "manufacturer", nullable = false, length = 128)
    private String manufacturer;

    @Column(name = "damage_description", columnDefinition = "TEXT")
    private String damageDescription;

}
