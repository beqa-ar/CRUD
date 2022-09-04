package dev.omedia.domains;


import lombok.*;

import javax.persistence.*;

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

    @Column(name = "name",nullable = false,length = 128)
    private String name;
}
