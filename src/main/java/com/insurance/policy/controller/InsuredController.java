package com.insurance.policy.controller;

import com.insurance.policy.model.DTO.InsuredRequest;
import com.insurance.policy.model.Insured;
import com.insurance.policy.service.InsuredService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insured")
public class InsuredController {

    @Autowired
    private InsuredService service;

    @PostMapping("")
    public ResponseEntity<?> createInsured(@Valid @RequestBody InsuredRequest request){
        Insured insured = service.createInsured(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(insured);
    }
}
