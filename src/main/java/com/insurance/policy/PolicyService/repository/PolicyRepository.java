package com.insurance.policy.PolicyService.repository;

import com.insurance.policy.PolicyService.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {
}
