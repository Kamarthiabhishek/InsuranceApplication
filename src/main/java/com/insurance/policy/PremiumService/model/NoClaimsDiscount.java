package com.insurance.policy.PremiumService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoClaimsDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productCode;
    private String productVersion;

    private Integer yearsFrom;
    private Integer yearsTo;

    private Double factor;

    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
}
