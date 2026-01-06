package com.insurance.policy.PremiumService.model;

import lombok.Data;

@Data
public class PremiumResponse {
    private Double basePremium;
    private Double loadingPremium;
    private Double discountPremium;
    private Double addonPremium;
    private Double tax;
    private Double finalPremium;
}
