package br.com.senai.goliath.trackrecord.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.senai.goliath.trackrecord.validator.EndDateMustBeGreaterThanStartDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndDateMustBeGreaterThanStartDateValidator.class)
public @interface EndDateMustBeGreaterThanStartDate {

        String message() default "{br.com.senai.goliath.trackrecord.annotations.EndDateMustBeGreaterThanStartDate}";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        boolean isEndDateRequired() default false;
}
