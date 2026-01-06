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
    private String vehicleType;
    private String fuelType;
    private Integer engineCCFrom;
    private Integer engineCCTo;
    private String cityZone;
    private Integer ratePercentage;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTill;
}
