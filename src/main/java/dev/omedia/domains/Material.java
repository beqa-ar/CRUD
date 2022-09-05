package dev.omedia.domains;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "l_materials")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Material {
    @Id
    @Column(name = "material_id")
    private long id;

    @NotNull
    @Size(min = 3,max = 128)
    @Column(name = "name",nullable = false,length = 128)
    private String name;
}
