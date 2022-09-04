package dev.omedia.domains;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branches")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Branch {
    @Id
    @Column(name = "branch_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "branch_id_seq")
    @SequenceGenerator(name = "branch_id_gen",sequenceName = "branch_id_seq",allocationSize = 1)
    private long id;

    @Column(name = "address" ,nullable = false,length = 256)
    private String address;

    @Column(name = "phone_number",nullable = false,length = 9)
    private String phoneNumber;
}
