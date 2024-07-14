package com.springBootweek2Homework.week2Homework.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {PrimeNumberValidator.class}
)
public @interface  PrimeNumberValidation {
    String message() default "Number is not Prime";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
