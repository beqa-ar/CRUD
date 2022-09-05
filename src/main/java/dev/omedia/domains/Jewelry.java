package dev.omedia.domains;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @OneToMany
    private List<Material> materials;

}
