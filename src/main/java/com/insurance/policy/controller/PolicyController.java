package com.insurance.policy.controller;

import com.insurance.policy.model.DTO.PolicyQuoteRequest;
import com.insurance.policy.model.DTO.PolicyRequest;
import com.insurance.policy.model.Policy;
import com.insurance.policy.service.PolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping("")
    public ResponseEntity<?> createQuote(@RequestBody PolicyQuoteRequest request){

        Policy policy = policyService.createPolicyQuote(request);
        if(policy != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(policy);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }

    @PutMapping("/{polNo}/accept")
    public ResponseEntity<?> acceptQuote(@PathVariable Integer polNo){
        try{
            return ResponseEntity.ok(policyService.acceptPolicy(polNo));
        }catch (IllegalStateException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{polNo}/reject")
    public ResponseEntity<?> rejectQuote(@PathVariable Integer polNo){
        try{
            return ResponseEntity.ok(policyService.rejectPolicy(polNo));
        }catch (IllegalStateException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
