package com.insurance.policy.PolicyService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Insured {

    @Id
    private Integer insuredId;
    private String firstName;
    private String surName;
    private String contact;
    private String address;
    private String gender;
    private String occupation;
    private LocalDate dob;
}
