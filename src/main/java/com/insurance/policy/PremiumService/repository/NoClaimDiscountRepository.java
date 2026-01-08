package com.insurance.policy.PremiumService.repository;

import com.insurance.policy.PremiumService.model.NoClaimsDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface NoClaimDiscountRepository extends JpaRepository<NoClaimsDiscount, Integer> {

    @Query("""
 SELECT n.factor
 FROM NoClaimsDiscount n
 WHERE n.productCode = :productCode
 AND n.productVersion = :productVersion
 AND :years BETWEEN n.yearsFrom AND n.yearsTo
 AND :today BETWEEN n.effectiveFrom AND n.effectiveTo
""")
    Optional<Double> findFactor(
            @Param("productCode") String productCode,
            @Param("productVersion") String productVersion,
            @Param("years") Integer years,
            @Param("today") LocalDate today
    );

}
