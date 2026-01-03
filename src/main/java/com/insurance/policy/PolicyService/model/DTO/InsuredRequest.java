package com.insurance.policy.PolicyService.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
//@ValidateDOB
public class InsuredRequest {

    private Integer insuredId;
//    @NotBlank(message = "Firstname is mandatory")
    private String firstName;
    private String surName;
//    @NotBlank(message = "Contact is mandatory")
    private String contact;
//    @NotBlank(message = "Address is mandatory")
    private String address;
//    @NotBlank(message = "Gender is mandatory")
    private String gender;
//    @NotBlank(message = "Occupation is mandatory")
    private String occupation;
//    @NotNull(message = "DOB is mandatory")
    private LocalDate dob;
}
