package dev.omedia.domains;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
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
    @Column(name = "payment_id",columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_gen",sequenceName = "payment_id_seq",allocationSize = 1)
    private long id;

    @NotNull
    @PastOrPresent
    @Column(name = "payment_date",nullable = false)
    private LocalDate paymentDate;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 10,fraction = 2)
    @Column(name = "amount_money_paid",nullable = false,precision = 10,scale = 2)
    private double AmountMoneyPaid;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "item_id")
    private Item item;
}
