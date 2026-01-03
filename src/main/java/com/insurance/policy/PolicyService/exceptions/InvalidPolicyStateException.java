package com.insurance.policy.PolicyService.exceptions;

import com.insurance.policy.PolicyService.enums.PolicyStatus;

public class InvalidPolicyStateException extends PolicyExceptions {

    public InvalidPolicyStateException(PolicyStatus status){
        super("Cannot Accept/Reject the Quote, Current state is "+status);
    }
}
