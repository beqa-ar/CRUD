package dev.omedia.domains;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jewelries")
public class Jewelry extends Item {

    @NotNull
    @Column(name = "description",nullable = false,columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Material> materials;

}
