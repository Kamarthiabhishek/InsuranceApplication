package com.insurance.policy.PolicyService.productriskmap;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductRiskDTO {

    private String productCode;
    private List<MasterData> risk;
}
