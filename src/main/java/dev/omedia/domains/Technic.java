package dev.omedia.domains;

import dev.omedia.enums.TechnicType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "technics")
public class Technic extends Item {

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TechnicType type;

    @NotNull
    @Size(min = 3,max = 128)
    @Column(name = "manufacturer", nullable = false, length = 128)
    private String manufacturer;

    @NotNull
    @Column(name = "damage_description", columnDefinition = "TEXT")
    private String damageDescription;

}
