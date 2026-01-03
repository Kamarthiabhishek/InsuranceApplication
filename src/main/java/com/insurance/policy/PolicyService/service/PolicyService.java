package com.insurance.policy.PolicyService.service;

import com.insurance.policy.PolicyService.enums.PolicyStatus;
import com.insurance.policy.PolicyService.exceptions.InvalidPolicyStateException;
import com.insurance.policy.PolicyService.exceptions.PolicyNotFoundException;
import com.insurance.policy.PolicyService.model.DTO.PolicyQuoteRequest;
import com.insurance.policy.PolicyService.model.Policy;
import com.insurance.policy.PolicyService.repository.PolicyRepository;
import com.insurance.policy.PolicyService.utils.GenerateRandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private GenerateRandomNumber randomNumber;

    public Policy createPolicyQuote(PolicyQuoteRequest request) {

        Policy policy = Policy.builder()
                .coverStartDate(request.getCoverStartDate())
                .coverEndDate(request.getCoverEndDate())
                .productType(request.getProductType())
                .riskId(request.getRiskId())
                .riskType(request.getRiskType())
                .riskAddress(request.getRiskAddress())
                .businessDescription(request.getBusinessDescription())
                .premiumAmount(request.getPremiumAmount())
                .polNo(randomNumber.generateNumber())
                .status(PolicyStatus.QUOTED)
                .build();

        return policyRepository.save(policy);

    }

    public Policy acceptPolicy(Integer polNo) {
        Policy policy = policyRepository.findById(polNo).orElseThrow(
                () -> new PolicyNotFoundException(polNo)
        );

        if (policy.getStatus() != PolicyStatus.QUOTED) {
            throw new InvalidPolicyStateException(policy.getStatus());
        }
        policy.setStatus(PolicyStatus.ACTIVE);
        return policyRepository.save(policy);
    }

    public Policy rejectPolicy(Integer polNo) {

        Policy policy = policyRepository.findById(polNo).orElseThrow(
                () -> new PolicyNotFoundException(polNo)
        );

        if (policy.getStatus() != PolicyStatus.QUOTED) {
            throw new InvalidPolicyStateException(policy.getStatus());
        }
        policy.setStatus(PolicyStatus.REJECTED);
        return policyRepository.save(policy);
    }
}
