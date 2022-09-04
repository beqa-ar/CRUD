package dev.omedia.domains;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jewelries")
public class Jewelry extends Item {

    @Column(name = "description",nullable = false,columnDefinition = "TEXT")
    private String description;
//
//    //TODO
//    private List<Material> materials;

}
