package com.insurance.policy.PolicyService.controller;

import com.insurance.policy.PolicyService.model.DTO.PolicyQuoteRequest;
import com.insurance.policy.PolicyService.model.Policy;
import com.insurance.policy.PolicyService.productriskmap.MasterDataService;
import com.insurance.policy.PolicyService.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private MasterDataService service;

    @PostMapping("")
    public ResponseEntity<?> createQuote(@RequestBody PolicyQuoteRequest request) {

        Policy policy = policyService.createPolicyQuote(request);
        if (policy != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(policy);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }

    @PutMapping("/{polNo}/accept")
    public ResponseEntity<?> acceptQuote(@PathVariable Integer polNo) {
        return ResponseEntity.ok(policyService.acceptPolicy(polNo));
    }

    @PutMapping("/{polNo}/reject")
    public ResponseEntity<?> rejectQuote(@PathVariable Integer polNo) {
        return ResponseEntity.ok(policyService.rejectPolicy(polNo));
    }

    @GetMapping("/product-risk")
    public ResponseEntity<?> getProductRisk(){
        return ResponseEntity.ok(service.getProductRisk());
    }
}
