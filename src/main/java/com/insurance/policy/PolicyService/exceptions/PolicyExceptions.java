package com.insurance.policy.PolicyService.exceptions;

public abstract class PolicyExceptions extends RuntimeException{

    public PolicyExceptions(String message){
        super(message);
    }
}
