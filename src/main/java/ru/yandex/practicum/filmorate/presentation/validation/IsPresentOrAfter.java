package ru.yandex.practicum.filmorate.presentation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsPresentOrAfterValidator.class})
public @interface IsPresentOrAfter {
    String message() default "Provided value is before constraint";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value();
}

