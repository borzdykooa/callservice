package com.borzdykooa.callservice.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberConstraintValidator.class)
public @interface PhoneNumberConstraint {

	String message() default "Phone number is invalid!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
