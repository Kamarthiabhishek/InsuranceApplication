package com.insurance.policy.model;

import com.insurance.policy.enums.PolicyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Policy {

    @Id
    private Integer polNo;

    private Integer extPolNo;

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;
    private LocalDate coverStartDate;
    private LocalDate coverEndDate;
    private String inceptionDate;
    private String cancellationDate;

    //Insured Details
    private String insuredId;
    private String insuredName;
    private String insuredType;

    //Risk Details
    private String productType;
    private int riskId;
    private String riskType;
    private String riskAddress;
    private String businessDescription;

    //Endorsements Details
    private String endorsementId;
    private String endorsementType;
    private String endorsementDate;

    //Financials
    private String premiumAmount;
    private String paymentStatus;
    private String sumInsured;
}
