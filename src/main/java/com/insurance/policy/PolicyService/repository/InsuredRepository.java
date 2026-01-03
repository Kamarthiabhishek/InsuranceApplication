package com.insurance.policy.PolicyService.repository;

import com.insurance.policy.PolicyService.model.Insured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuredRepository extends JpaRepository<Insured, Integer> {
}
