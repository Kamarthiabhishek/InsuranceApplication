//package com.insurance.policy.validations;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Target({ ElementType.TYPE })
//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = DOBValidator.class)
//public @interface ValidateDOB {
//
//    String message() default "Date of Birth must be a past date";
//
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
