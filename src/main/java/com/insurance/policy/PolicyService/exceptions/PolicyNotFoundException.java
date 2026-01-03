package com.insurance.policy.PolicyService.exceptions;

public class PolicyNotFoundException extends PolicyExceptions {

    public PolicyNotFoundException(Integer polNo){
        super("Policy not found with policy number "+polNo);
    }
}
