package com.insurance.policy.PolicyService.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PolicyRequest {

//    @NotNull
    private int polNo;
//    @NotNull
    private int extPolNo;
//    @NotBlank(message = "")
    private String status;
//    @NotNull(message = "Start date is mandatory")
    private LocalDate coverStartDate;
//    @NotNull(message = "End date is mandatory")
    private LocalDate coverEndDate;
//    @NotBlank
    private String inceptionDate;
//    @NotBlank
    private String cancellationDate;

    //Insured Details
//    @NotBlank
    private String insuredId;
//    @NotBlank
    private String insuredName;
//    @NotBlank
    private String insuredType;

    //Risk Details
//    @NotBlank
    private String productType;
//    @NotNull
    private int riskId;
//    @NotBlank
    private String riskType;
//    @NotBlank
    private String riskAddress;
//    @NotBlank
    private String businessDescription;

    //Endorsements Details
//    @NotBlank
    private String endorsementId;
//    @NotBlank
    private String endorsementType;
//    @NotBlank
    private String endorsementDate;

    //Financials
//    @NotBlank
    private String premiumAmount;
//    @NotBlank
    private String paymentStatus;
//    @NotBlank
    private String sumInsured;
}
