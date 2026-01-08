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

//    @Autowired
//    private AddonHelper addonHelper;

    public PremiumResponse calculatePremium(PremiumRequest request){

        LocalDate today = LocalDate.now();
        //Calculate the BASE PREMIUM    --> STEP1

        System.out.println("inside BASE RATE function");
        BaseRate baseRate = baseRateRepository.findBaseRate(
                request.getProductCode(), request.getProductVersion(), request.getVehicleType(),
                request.getFuelType(), request.getEngineCC(), request.getCityCategory().equals("METRO") ? "A" : "B" , today
        ).orElseThrow(() -> new RuntimeException("Base Rate Not Found"));

        double basePremium = request.getIdv() * baseRate.getRatePercentage() / 100;
        System.out.println("BASE RATE calculated" + basePremium);


        //Calculate the AGE FACTOR
        System.out.println("inside AGE FACTOR function");
        double ageRisking = vehicleAgeRiskingRepository.findFactor(
                request.getProductCode(), request.getProductVersion(), request.getVehicleAge(), today
        );
        double premiumAfterAgeRisk = basePremium * ageRisking;
        System.out.println("AGE FACTOR calculated" + premiumAfterAgeRisk);

        //Calculate the CITY RISK FACTOR
        System.out.println("inside CITY RISK FACTOR");
        double cityRisking = cityRiskRepository.findFactor(
                request.getProductCode(), request.getProductVersion(), request.getCityCategory(),today
        );
        double premiumAfterCityRisking = premiumAfterAgeRisk * cityRisking;
        System.out.println("CITY RISK FACTOR calculated" + premiumAfterCityRisking);

        // Calculate the discount
        System.out.println("inside DISCOUNT function");

        Double discountFactor = noClaimDiscountRepository.findFactor(
                request.getProductCode(),
                request.getProductVersion(),
                request.getNoClaimsYear(),
                today
        ).orElse(1.0);
        System.out.println(discountFactor);

        System.out.println("Printing values for check 1. productCode"+ request.getProductCode()+
                "2. productVersion"+request.getProductVersion()+"3. idv "+ request.getIdv()+
                "4. engineCC "+ request.getEngineCC() +" 5. VehicleAge " + request.getVehicleAge()+
                "5. fuelType "+ request.getFuelType() + " 6. vehicleType "+request.getVehicleType()+
                " 7. cityCategory "+ request.getCityCategory() + " 8. noClaimsYear "+request.getNoClaimsYear()+
                " 8. ZERO_DEP "+request.isZeroDep()+" 9. RSA "+request.isRsa()
        );

        double premiumAfterDiscount = premiumAfterCityRisking * discountFactor;

        System.out.println("NCD factor: " + discountFactor);
        System.out.println("Premium Amount "+premiumAfterDiscount);
        //Calculate the Addons
//        double addOn = 0;
//
//        if(request.isZeroDep()){
//            addOn += addonHelper.calculatorAddOn(
//                    request.getProductCode(), request.getProductVersion(),"ZERO_DEP",basePremium, today);
//        }
//
//        if(request.isRsa()){
//            addOn += addonHelper.calculatorAddOn(request.getProductCode(), request.getProductVersion(),"RSA",basePremium, today);
//        }
//
//        System.out.println("completed ADDON function");

//        double netPremium = premiumAfterDiscount + addOn;
        //Adding 18% tax
        double tax = premiumAfterDiscount * 0.18;

        return PremiumResponse.builder().
                basePremium(basePremium)
                .loadingPremium(premiumAfterCityRisking)
                .discountPremium(premiumAfterDiscount)
                .tax(tax)
                .finalPremium(premiumAfterDiscount + tax).build();
    }
}
