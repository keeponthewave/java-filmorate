package ru.yandex.practicum.filmorate.gateways.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;


public class IsPresentOrAfterValidator implements ConstraintValidator<IsPresentOrAfter, LocalDate> {
    private LocalDate constraint;

    @Override
    public void initialize(IsPresentOrAfter constraintAnnotation) {
        constraint = LocalDate.parse(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.isAfter(constraint) || localDate.isEqual(constraint);
    }
}
