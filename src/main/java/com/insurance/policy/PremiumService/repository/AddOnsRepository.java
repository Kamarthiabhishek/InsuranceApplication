package com.insurance.policy.PremiumService.repository;

import com.insurance.policy.PremiumService.model.AddOns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AddOnsRepository extends JpaRepository<AddOns, Integer> {

    @Query("""
    SELECT a
    FROM AddOns a
    WHERE a.productCode = :productCode
      AND a.productVersion = :productVersion
      AND a.addonCode = :addonCode
      AND :date BETWEEN a.effectiveFrom AND a.effectiveTo
    ORDER BY a.effectiveFrom DESC
    """)
    List<AddOns> findActiveAddOns(
            @Param("productCode") String productCode,
            @Param("productVersion") String productVersion,
            @Param("addonCode") String addonCode,
            @Param("date") LocalDate date
    );
}
