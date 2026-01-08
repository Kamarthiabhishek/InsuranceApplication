//import com.insurance.policy.PremiumService.model.AddOns;
//import org.springframework.data.jpa.repository.Query;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Query("""
//SELECT a
//FROM AddOns a
//WHERE a.productCode = :productCode
//  AND a.productVersion = :productVersion
//  AND a.addonCode = :addonCode
//  AND :date BETWEEN a.effectiveFrom AND a.effectiveTo
//ORDER BY a.effectiveFrom DESC
//""")
//List<AddOns> findAddons(
//        String productCode,
//        String productVersion,
//        String addonCode,
//        LocalDate date
//);
