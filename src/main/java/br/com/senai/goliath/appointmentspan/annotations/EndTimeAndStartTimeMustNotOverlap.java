package br.com.senai.goliath.appointmentspan.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.senai.goliath.appointmentspan.validator.EndTimeAndStartTimeMustNotOverlapValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndTimeAndStartTimeMustNotOverlapValidator.class)
public @interface EndTimeAndStartTimeMustNotOverlap {

        String message() default "{br.com.senai.goliath.appointmentspan.annotations.EndTimeAndStartTimeMustNotOverlap}";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
}
