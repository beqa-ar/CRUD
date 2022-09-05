package dev.omedia.domains;

import dev.omedia.enums.OdometerUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Year;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "automobiles")
public class Automobile extends Item {

    @NotNull
    @Size(min = 3,max = 64)
    @Column(name = "manufacturer", nullable = false, length = 64)
    private String manufacturer;

    @NotNull
    @Size(min = 3,max = 64)
    @Column(name = "model", nullable = false, length = 64)
    private String model;

    @NotNull
    @PastOrPresent
    @Column(name = "manufacture_year", nullable = false)
    private Year manufactureYear;

    @NotNull
    @Max(value = 999_999)
    @Column(name = "odometer",nullable = false,length = 6)
    private long odometer;

    @NotNull
    @Column(name ="odometer_unit")
    @Enumerated(value = EnumType.STRING)
    private OdometerUnit odometerUnit;
}
