package com.insurance.policy.PremiumService.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PremiumResponse {
    private Double basePremium;
    private Double loadingPremium;
    private Double discountPremium;
    private Double addonPremium;
    private Double tax;
    private Double finalPremium;
}
