package dev.omedia.domains;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payment {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_gen",sequenceName = "payment_id_seq",allocationSize = 1)
    private long id;

    @PastOrPresent
    @Column(name = "payment_date",nullable = false)
    private LocalDate paymentDate;

    @Column(name = "amount_money_paid",nullable = false,precision = 10,scale = 2)
    private double AmountMoneyPaid;

    @Column(name = "item_id",nullable = false)
    private long itemId;
}
