//package com.insurance.policy.validations;
//
//import com.insurance.policy.model.DTO.InsuredRequest;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//import java.time.LocalDate;
//
//public class DOBValidator implements ConstraintValidator<ValidateDOB, InsuredRequest> {
//
//    @Override
//    public boolean isValid(InsuredRequest insuredRequest,
//                           ConstraintValidatorContext context) {
//
//        if (insuredRequest == null || insuredRequest.getDob() == null) {
//            return true; // Let @NotNull handle null cases
//        }
//
//        if (insuredRequest.getDob().isBefore(LocalDate.now())) {
//            return true;
//        }
//
//        context.disableDefaultConstraintViolation();
//        context.buildConstraintViolationWithTemplate(
//                        "Date of Birth must be a past date"
//                ).addPropertyNode("dob")
//                .addConstraintViolation();
//
//        return false;
//    }
//}
