package com.insurance.policy.PremiumService.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PremiumRequest {

    private String productCode;
    private String productVersion;

    private Double idv;
    private Integer engineCC;
    private Integer vehicleAge;

    private String fuelType;
    private String vehicleType;
    private String cityCategory;

    private Integer noClaimsYear;

    private boolean zeroDep;
    private boolean rsa;

}
