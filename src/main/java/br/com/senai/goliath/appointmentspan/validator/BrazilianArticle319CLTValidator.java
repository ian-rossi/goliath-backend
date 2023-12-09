package br.com.senai.goliath.appointmentspan.validator;

import java.time.DayOfWeek;

import br.com.senai.goliath.appointmentspan.annotations.EndTimeAndStartTimeMustNotOverlap;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrazilianArticle319CLTValidator implements ConstraintValidator<EndTimeAndStartTimeMustNotOverlap, DayOfWeek> {

        @Override
        public boolean isValid(final DayOfWeek value, ConstraintValidatorContext context) {
                return !DayOfWeek.SUNDAY.equals(value);
        }

}
