package com.springBootweek2Homework.week2Homework.annotations;

import com.springBootweek2Homework.week2Homework.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation,Integer> {

    @Override
    public boolean isValid(Integer num, ConstraintValidatorContext constraintValidatorContext) {
        if(num==null){
             throw new ResourceNotFoundException("Null value is not allowed");
        }
        for (int i=2;i<num;i++){
            if(num%i==0)
                return false;
        }
        return true;
    }
}
