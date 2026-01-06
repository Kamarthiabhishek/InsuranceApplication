package com.insurance.policy.PremiumService.repository;

import com.insurance.policy.PremiumService.model.BaseRate;
import com.insurance.policy.PremiumService.model.NoClaimsDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface NoClaimDiscountRepository extends JpaRepository<NoClaimsDiscount, Integer> {

    @Query("""
        SELECT n.factor FROM NcbDiscount n
        WHERE n.productCode = :productCode
          AND n.productVersion = :productVersion
          AND :years BETWEEN n.yearsFrom AND n.yearsTo
          AND :today BETWEEN n.effectiveFrom AND n.effectiveTo
    """)
    Optional<Double> findFactor(
            String productCode,
            String productVersion,
            Integer years,
            LocalDate today
    );
}
