//package com.insurance.policy.PremiumService.service;
//
//import com.insurance.policy.PremiumService.model.AddOns;
//import com.insurance.policy.PremiumService.repository.AddOnsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Component
//public class AddonHelper {
//
//    @Autowired
//    private AddOnsRepository addOnsRepository;
//
//    public double calculatorAddOn(
//            String productCode, String productVersion, String pricingType,
//            double basePremium, LocalDate today
//    ) {
//
//        System.out.println("inside ADDON function");
//        //Check for the entries in the DB and return Config missing Error if not present
//        AddOns addOns = addOnsRepository.findAddons(
//                productCode, productVersion, pricingType, today
//        ).orElseThrow(() -> new RuntimeException("Not Found Exception"));
//
//        System.out.println(addOns);
//
//        double addon =0;
//
//        //If the pricingType is selected as percentage then the amount has to be bought from basePremium EX: (12000 * 3.2) / 100 that means 3.2% of 12000
//        if ("PRECENT".equals(addOns.getPricingType())) {
//            if()
//        }
//            return (basePremium * addOns.getPricingValue()) / 100;
//        //If the pricingType is selected as FLAT then the flat amount has to be returned
//        if ("FLAT".equals((addOns.getPricingType())))
//            return (addOns.getPricingValue());
//
//        return 0.0;
//    }
//}
