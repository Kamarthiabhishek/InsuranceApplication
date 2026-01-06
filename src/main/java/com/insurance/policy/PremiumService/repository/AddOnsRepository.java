package com.insurance.policy.PremiumService.repository;

import com.insurance.policy.PremiumService.model.AddOns;
import com.insurance.policy.PremiumService.model.BaseRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AddOnsRepository extends JpaRepository<AddOns, Integer> {

    @Query("""
        SELECT a FROM AddonRate a
        WHERE a.productCode = :productCode
          AND a.productVersion = :productVersion
          AND a.addonCode = :addonCode
          AND :today BETWEEN a.effectiveFrom AND a.effectiveTo
    """)
    Optional<AddOns> findAddon(
            String productCode,
            String productVersion,
            String addonCode,
            LocalDate today
    );
}
