package br.com.senai.goliath.term.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.senai.goliath.term.validator.LessonTimeMustBeDivisibleByAppointmentSpansValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LessonTimeMustBeDivisibleByAppointmentSpansValidator.class)
public @interface LessonTimeMustBeDivisibleByAppointmentSpans {

        String message() default "{br.com.senai.goliath.term.annotations.LessonTimeMustBeDivisibleByAppointmentSpans}";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

}
