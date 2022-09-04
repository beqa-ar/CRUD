package dev.omedia.domains;

import dev.omedia.enums.LoanStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_id_seq")
    @SequenceGenerator(name = "item_id_gen",sequenceName = "item_id_seq",allocationSize = 1)
    private long id;

    @Column(name = "owner_personal_no",unique = true,nullable = false,length = 11)
    private long ownerPersonalNo;

    @PastOrPresent
    @Column(name = "loan_start_date",nullable = false)
    private LocalDate loanStartDate;

    @OneToOne
    @JoinColumn(name ="branch_id")
    private Branch branch;

    @Column(name = "amount_money_paid",nullable = false,precision = 10,scale = 2)
    private double AmountMoneyPaid;

    @Column(name = "monthly_payment",nullable = false,precision = 10,scale = 2)
    private double monthlyPayment;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoanStatus status;

    @PastOrPresent
    @Column(name = "status_update_date", nullable = false)
    private LocalDate statusUpdateDate;
}
