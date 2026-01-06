package com.insurance.policy.PremiumService.service;

import com.insurance.policy.PremiumService.model.BaseRate;
import com.insurance.policy.PremiumService.model.PremiumRequest;
import com.insurance.policy.PremiumService.model.PremiumResponse;
import com.insurance.policy.PremiumService.model.VehicleAgeRisking;
import com.insurance.policy.PremiumService.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PremiumService {

    @Autowired
    private BaseRateRepository baseRateRepository;

    @Autowired
    private VehicleAgeRiskingRepository vehicleAgeRiskingRepository;

    @Autowired
    private CityRiskRepository cityRiskRepository;

    @Autowired
    private NoClaimDiscountRepository noClaimDiscountRepository;

    @Autowired
    private AddonHelper addonHelper;

    public PremiumResponse calculatePremium(PremiumRequest request){

        LocalDate today = LocalDate.now();
        //Calculate the BASE PREMIUM    --> STEP1
        BaseRate baseRate = baseRateRepository.findBaseRate(
                request.getProductCode(), request.getProductVersion(), request.getVehicleType(),
                request.getFuelType(), request.getEngineCC(), request.getCityCategory().equals("METRO") ? "A" : "B" , today
        ).orElseThrow(() -> new RuntimeException("Base Rate Not Found"));

        double basePremium = request.getIdv() * baseRate.getRatePercentage() / 100;


        //Calculate the AGE FACTOR
        double ageRisking = vehicleAgeRiskingRepository.findFactor(
                request.getProductCode(), request.getProductVersion(), request.getVehicleAge(), today
        );
        double premiumAfterAgeRisk = basePremium * ageRisking;

        //Calculate the CITY RISK FACTOR
        double cityRisking = cityRiskRepository.findFactor(
                request.getProductCode(), request.getProductVersion(), request.getCityCategory(),today
        );
        double premiumAfterCityRisking = premiumAfterAgeRisk * cityRisking;

        //Calculate the discount
        double discount = noClaimDiscountRepository.findFactor(
                request.getProductCode(), request.getProductVersion(), request.getNoClaimsYear(), today
        );
        double premiumAfterDiscount = premiumAfterCityRisking - discount;

        //Calculate the Addons
        double addOn = 0;

        if(request.isZeroDep()){
            addOn += addonHelper.calculatorAddOn(
                    request.getProductCode(), request.getProductVersion(),"ZERO_DEP",basePremium, today);
        }

        if(request.isRsa()){
            addOn += addonHelper.calculatorAddOn(request.getProductCode(), request.getProductVersion(),"RSA",basePremium, today);
        }

        double netPremium = premiumAfterDiscount + addOn;
        //Adding 18% tax
        double tax = netPremium * 0.18;

        return PremiumResponse.builder().
                basePremium(basePremium)
                .loadingPremium(premiumAfterCityRisking)
                .discountPremium(premiumAfterDiscount)
                .addonPremium(netPremium)
                .tax(tax)
                .finalPremium(netPremium + tax).build();
    }
}
