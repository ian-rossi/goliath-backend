package br.com.senai.goliath.timespan.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.senai.goliath.timespan.validator.EndTimeMustBeGreaterThanStartTimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndTimeMustBeGreaterThanStartTimeValidator.class)
public @interface EndTimeMustBeGreaterThanStartTime {

        String message() default "{br.com.senai.goliath.timespan.annotations.EndTimeMustBeGreaterThanStartTime}";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

}
