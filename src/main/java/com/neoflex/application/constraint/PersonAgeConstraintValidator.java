package com.neoflex.application.constraint;

import com.neoflex.application.service.ApplicationService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class PersonAgeConstraintValidator implements ConstraintValidator <PersonAgeConstraint, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext constraintValidatorContext) {
        return ApplicationService.calculateAge(birthDate) >= 18;
    }
}
