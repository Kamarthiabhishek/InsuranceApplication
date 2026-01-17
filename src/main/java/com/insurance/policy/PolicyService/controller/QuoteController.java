package com.insurance.policy.PolicyService.controller;

import com.insurance.policy.PolicyService.model.DTO.QuoteRequest;
import com.insurance.policy.PolicyService.model.Quote;
import com.insurance.policy.PolicyService.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quote")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuote(@RequestBody QuoteRequest request) {

        try {
            Quote quote = quoteService.createQuote(request);
            return new ResponseEntity<>(quote, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
