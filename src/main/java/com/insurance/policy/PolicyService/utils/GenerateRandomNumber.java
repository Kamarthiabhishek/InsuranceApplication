package com.insurance.policy.PolicyService.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class GenerateRandomNumber {

    public int generateNumber(){
        return ThreadLocalRandom.current().nextInt(1000000,9999999);
    }
}
