package br.com.senai.goliath.timespan.validator;

import br.com.senai.goliath.timespan.annotations.EndTimeMustBeGreaterThanStartTime;
import br.com.senai.goliath.timespan.model.TimeSpanEntity;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class EndTimeMustBeGreaterThanStartTimeValidator implements ConstraintValidator<EndTimeMustBeGreaterThanStartTime, TimeSpanEntity> {

        @Override
        public boolean isValid(final TimeSpanEntity value, final ConstraintValidatorContext context) {
                final var startingTime = value.getStartingTime();
                final var endingTime = value.getEndingTime();
                return startingTime.isBefore(endingTime);
        }

}
