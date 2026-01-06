package com.insurance.policy.PremiumService.repository;

import com.insurance.policy.PremiumService.model.BaseRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BaseRateRepository extends JpaRepository<BaseRate, Integer> {

    @Query("""
        SELECT b FROM BaseRate b
        WHERE b.productCode = :productCode
          AND b.productVersion = :productVersion
          AND b.vehicleType = :vehicleType
          AND b.fuelType = :fuelType
          AND :engineCc BETWEEN b.engineCcFrom AND b.engineCcTo
          AND b.zone = :zone
          AND :today BETWEEN b.effectiveFrom AND b.effectiveTo
    """)
    Optional<BaseRate> findBaseRate(
            String productCode,
            String productVersion,
            String vehicleType,
            String fuelType,
            Integer engineCc,
            String zone,
            LocalDate today
    );
}
