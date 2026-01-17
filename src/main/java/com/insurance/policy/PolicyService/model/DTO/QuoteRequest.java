package com.insurance.policy.PolicyService.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteRequest {

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
