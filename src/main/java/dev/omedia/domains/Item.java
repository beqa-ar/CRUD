package dev.omedia.domains;

import dev.omedia.enums.LoanStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Item {

    @Id
    @Column(name = "item_id",columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_id_seq")
    @SequenceGenerator(name = "item_id_gen",sequenceName = "item_id_seq",allocationSize = 1)
    private long id;


    @NotNull
    @Pattern(regexp="\\d{11}",message="only numbers! length must be 11")
    @Column(name = "owner_personal_no",nullable = false,length = 11)
    private String ownerPersonalNo;

    @NotNull
    @PastOrPresent
    @Column(name = "loan_start_date",nullable = false)
    private LocalDate loanStartDate;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 10,fraction = 2)
    @Column(name = "amount_money_paid",nullable = false,precision = 10,scale = 2)
    private double AmountMoneyPaid;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 10,fraction = 2)
    @Column(name = "monthly_payment",nullable = false,precision = 10,scale = 2)
    private double monthlyPayment;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "loan_status", nullable = false)
    private LoanStatus loanStatus;

    @NotNull
    @PastOrPresent
    @Column(name = "status_update_date", nullable = false)
    private LocalDate statusUpdateDate;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name ="branch_id")
    private Branch branch;

}
