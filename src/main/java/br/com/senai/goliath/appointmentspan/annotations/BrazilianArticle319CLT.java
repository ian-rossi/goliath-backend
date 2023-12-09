package br.com.senai.goliath.appointmentspan.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.senai.goliath.appointmentspan.validator.BrazilianArticle319CLTValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BrazilianArticle319CLTValidator.class)
public @interface BrazilianArticle319CLT {

        String message() default "{br.com.senai.goliath.appointmentspan.annotations.BrazilianArticle319CLT}";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
}
