package com.insurance.policy.PolicyService.model.DTO;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PolicyQuoteRequest {

    private LocalDate coverStartDate;
    private LocalDate coverEndDate;
    private String productType;
    private int riskId;
    private String riskType;
    private String riskAddress;
    private String businessDescription;
    private String premiumAmount;
}
