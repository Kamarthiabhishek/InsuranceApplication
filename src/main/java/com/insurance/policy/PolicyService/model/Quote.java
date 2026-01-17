package com.insurance.policy.PolicyService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quoteId;

    private String productCode;
    private String productVersion;

    private Double idv;
    private Integer engineCC;
    private Integer vehicleAge;

    private String fuelType;
    private String vehicleType;
    private String cityCategory;

    private Integer noClaimsYear;
    private String pricingType;

    private boolean zeroDep;
    private boolean rsa;

    // Premium details
    private Double netPremium;

    private String status;

    private LocalDate coverStartDate;
    private LocalDate coverEndDate;

}
