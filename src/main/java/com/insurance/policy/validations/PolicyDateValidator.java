//package com.insurance.policy.validations;
//
//import com.insurance.policy.model.DTO.PolicyRequest;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class PolicyDateValidator implements ConstraintValidator<ValidatePolicyDates, PolicyRequest> {
//
//
//    @Override
//    public boolean isValid(PolicyRequest request, ConstraintValidatorContext constraintValidatorContext) {
//
//
//        if(request.getCoverStartDate() == null || request.getCoverEndDate()==null) return true;
//
//        if(request.getCoverStartDate().isBefore(request.getCoverEndDate())) return true;
//
//        constraintValidatorContext.disableDefaultConstraintViolation();
//        constraintValidatorContext.buildConstraintViolationWithTemplate(
//                "End Date must be after start date"
//        ).addPropertyNode("endDate").addConstraintViolation();
//        return false;
//    }
//}
