package com.insurance.policy.PremiumService.repository;

import com.insurance.policy.PremiumService.model.BaseRate;
import com.insurance.policy.PremiumService.model.CityRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CityRiskRepository extends JpaRepository<CityRisk, Integer> {

    @Query("""
        SELECT c.factor FROM CityRisk c
        WHERE c.productCode = :productCode
          AND c.productVersion = :productVersion
          AND c.cityCategory = :cityCategory
          AND :today BETWEEN c.effectiveFrom AND c.effectiveTo
    """)
    Optional<Double> findFactor(
            String productCode,
            String productVersion,
            String cityCategory,
            LocalDate today
    );
}
