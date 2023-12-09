package br.com.senai.goliath.lessongroupterm.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.senai.goliath.lessongroupterm.validator.SubjectLessonHoursMustFitTermIntervalValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SubjectLessonHoursMustFitTermIntervalValidator.class)
public @interface SubjectLessonHoursMustFitTermInterval {

        String message() default "{br.com.senai.goliath.lessongroupterm.annotations.SubjectLessonHoursMustFitTermInterval}";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

}
