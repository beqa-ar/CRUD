package dev.omedia.domains;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branches")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Branch {
    @Id
    @Column(name = "branch_id",columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "branch_id_seq")
    @SequenceGenerator(name = "branch_id_gen",sequenceName = "branch_id_seq",allocationSize = 1)
    private long id;

    @NotNull
    @Size(min = 3,max = 256)
    @Column(name = "address" ,nullable = false,length = 256)
    private String address;

    @NotNull
    @Pattern(regexp="\\d{9}",message="only numbers! length must be 9")
    @Column(name = "phone_number",nullable = false,length = 9)
    private String phoneNumber;
}
