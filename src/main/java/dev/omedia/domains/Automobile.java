package dev.omedia.domains;

import dev.omedia.enums.OdometerUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.Year;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "automobiles")
public class Automobile extends Item {

    @Column(name = "manufacturer", nullable = false, length = 64)
    private String manufacturer;

    @Column(name = "model", nullable = false, length = 64)
    private String model;

    @PastOrPresent
    @Column(name = "manufacture_year", nullable = false)
    private Year manufactureYear;

    @Column(name = "odometer",nullable = false,length = 8)
    private long odometer;

    @Column(name ="odometer_unit")
    @Enumerated(value = EnumType.STRING)
    private OdometerUnit odometerUnit;
}
