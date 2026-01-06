package com.insurance.policy.PremiumService.repository;

import com.insurance.policy.PremiumService.model.BaseRate;
import com.insurance.policy.PremiumService.model.VehicleAgeRisking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface VehicleAgeRiskingRepository extends JpaRepository<VehicleAgeRisking, Integer> {

    @Query("""
        SELECT v.factor FROM VehicleAgeRisk v
        WHERE v.productCode = :productCode
          AND v.productVersion = :productVersion
          AND :vehicleAge BETWEEN v.ageFrom AND v.ageTo
          AND :today BETWEEN v.effectiveFrom AND v.effectiveTo
    """)
    Optional<Double> findFactor(
            String productCode,
            String productVersion,
            Integer vehicleAge,
            LocalDate today
    );
}
