package com.insurance.policy.service;

import com.insurance.policy.enums.PolicyStatus;
import com.insurance.policy.model.DTO.PolicyQuoteRequest;
import com.insurance.policy.model.DTO.PolicyRequest;
import com.insurance.policy.model.Policy;
import com.insurance.policy.repository.PolicyRepository;
import com.insurance.policy.utils.GenerateRandomNumber;
import jakarta.persistence.EntityNotFoundException;
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
                () -> new EntityNotFoundException("Policy Not Found")
        );

        if (policy.getStatus() != PolicyStatus.QUOTED) {
            throw new IllegalStateException("Policy : " + polNo + " cannot be accepted. Current status " + policy.getStatus());
        }
        policy.setStatus(PolicyStatus.ACTIVE);
        return policyRepository.save(policy);
    }

    public Policy rejectPolicy(Integer polNo) {

        Policy policy = policyRepository.findById(polNo).orElseThrow(
                () -> new EntityNotFoundException("Policy Not Found")
        );

        if (policy.getStatus() != PolicyStatus.QUOTED) {
            throw new IllegalStateException("Policy : " + polNo+ " cannot be rejected, Current status is " + policy.getStatus());
        }
        policy.setStatus(PolicyStatus.REJECTED);

        return policyRepository.save(policy);
    }
}
