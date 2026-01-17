package com.insurance.policy.PremiumService.controller;

import com.insurance.policy.PremiumService.model.PremiumRequest;
import com.insurance.policy.PremiumService.service.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/premium")
public class PremiumController {

    @Autowired
    private PremiumService premiumService;

    @PostMapping("/calculate")
    public Double calculatePremium(@RequestBody PremiumRequest request){
        return premiumService.calculatePremium(request);
    }
}
