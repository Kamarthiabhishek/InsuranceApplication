package com.insurance.policy.PremiumService.service;

import com.insurance.policy.PremiumService.model.*;
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
    private AddOnsRepository addOnsRepository;

//    @Autowired
//    private AddonHelper addonHelper;

    public Double calculatePremium(PremiumRequest request) {

        LocalDate today = LocalDate.now();
        //Calculate the BASE PREMIUM    --> STEP1

        System.out.println("inside BASE RATE function");
        BaseRate baseRate = baseRateRepository.findBaseRate(
                request.getProductCode(), request.getProductVersion(), request.getVehicleType(),
                request.getFuelType(), request.getEngineCC(), request.getCityCategory().equals("METRO") ? "A" : "B", today
        ).orElseThrow(() -> new RuntimeException("Base Rate Not Found"));

        double basePremium = request.getIdv() * baseRate.getRatePercentage() / 100;
        System.out.println("BASE RATE calculated" + basePremium);


        //Calculate the AGE FACTOR
        System.out.println("inside AGE FACTOR function");
        double ageRisking = vehicleAgeRiskingRepository.findFactor(
                request.getProductCode(), request.getProductVersion(), request.getVehicleAge(), today
        );
        double premiumAfterAgeRisk = basePremium * ageRisking;


        //Calculate the CITY RISK FACTOR
        System.out.println("inside CITY RISK FACTOR");
        double cityRisking = cityRiskRepository.findFactor(
                request.getProductCode(), request.getProductVersion(), request.getCityCategory(), today
        );
        double premiumAfterCityRisking = premiumAfterAgeRisk * cityRisking;

        double loadingValue = ageRisking + cityRisking;


        // Calculate the discount
        System.out.println("inside DISCOUNT function");

        Double discountFactor = noClaimDiscountRepository.findFactor(
                request.getProductCode(),
                request.getProductVersion(),
                request.getNoClaimsYear(),
                today
        ).orElse(1.0);
        double discountValue = premiumAfterCityRisking * discountFactor;
        double premiumAfterDiscount = premiumAfterCityRisking - discountValue;

        //Calculate the Addons
        System.out.println("Started ADDON");
        double addOn = 0.0;
        System.out.println("inside ADDON function");

        if (request.isZeroDep()) {

            AddOns addOns = addOnsRepository
                    .findActiveAddOns(
                            request.getProductCode(),
                            request.getProductVersion(),
                            "ZERO_DEP",
                            today
                    )
                    .stream()
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException("ZERO_DEP addon config not found"));

            if ("PERCENT".equalsIgnoreCase(addOns.getPricingType())) {
                addOn += (basePremium * addOns.getPricingValue()) / 100;
            } else {
                addOn += addOns.getPricingValue();
            }

        } else if (request.isRsa()) {

            AddOns addOns = addOnsRepository
                    .findActiveAddOns(
                            request.getProductCode(),
                            request.getProductVersion(),
                            "RSA",
                            today
                    )
                    .stream()
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException("RSA addon config not found"));

            if ("PERCENT".equalsIgnoreCase(addOns.getPricingType())) {
                addOn += (basePremium * addOns.getPricingValue()) / 100;
            } else {
                addOn += addOns.getPricingValue();
            }
        }

        double premiumAfterAddons = premiumAfterDiscount + addOn;
        System.out.println("completed ADDON function");

        //Adding 18% tax
        double tax = premiumAfterAddons * 0.18;

        double netPremium = premiumAfterDiscount + tax;

        System.out.println("Base Premium : " + basePremium);
        System.out.println("Loading Value : " + (ageRisking + cityRisking));
        System.out.println("Loading Premium : " + premiumAfterCityRisking);
        System.out.println("Discount Percentage : " + discountFactor);
        System.out.println("Premium Amount after discount " + premiumAfterDiscount);
        System.out.println("Addon value : " + addOn);
        System.out.println("Premium  after addons : " + premiumAfterAddons);
        System.out.println("Tax Value : " + tax);
        System.out.println("Net Premium : " + netPremium);


        return netPremium;
    }
}
