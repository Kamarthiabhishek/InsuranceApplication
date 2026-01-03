package com.insurance.policy.PolicyService.service;

import com.insurance.policy.PolicyService.model.DTO.InsuredRequest;
import com.insurance.policy.PolicyService.model.Insured;
import com.insurance.policy.PolicyService.repository.InsuredRepository;
import com.insurance.policy.PolicyService.utils.GenerateRandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuredService {

    @Autowired
    private InsuredRepository insuredRepository;

    @Autowired
    private GenerateRandomNumber randomNumber;

    public Insured createInsured(InsuredRequest request){

        int insuredId = randomNumber.generateNumber();

        Insured insured = Insured.builder()
                .insuredId(insuredId)
                .firstName(request.getFirstName())
                .surName(request.getSurName())
                .contact(request.getContact())
                .address(request.getAddress())
                .gender(request.getGender())
                .occupation(request.getOccupation())
                .dob(request.getDob()).build();

        insuredRepository.save(insured);
        return insured;
    }
}
