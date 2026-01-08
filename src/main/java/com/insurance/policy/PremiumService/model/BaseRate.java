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
public class BaseRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer riskId;

    private String productCode;
    private String productVersion;
    private String vehicleType;
    private String fuelType;
    private Integer engineCcFrom;
    private Integer engineCcTo;
    private String zone;
    private Double ratePercentage;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
}
