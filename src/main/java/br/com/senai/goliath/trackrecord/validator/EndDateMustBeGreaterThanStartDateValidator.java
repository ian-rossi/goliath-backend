package br.com.senai.goliath.trackrecord.validator;

import br.com.senai.goliath.trackrecord.annotations.EndDateMustBeGreaterThanStartDate;
import br.com.senai.goliath.trackrecord.model.TrackRecordEntity;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EndDateMustBeGreaterThanStartDateValidator implements ConstraintValidator<EndDateMustBeGreaterThanStartDate, TrackRecordEntity> {

        boolean isEndDateRequired;

        @Override
        public void initialize(final EndDateMustBeGreaterThanStartDate constraintAnnotation) {
                this.isEndDateRequired = constraintAnnotation.isEndDateRequired();
        }

        @Override
        public boolean isValid(final TrackRecordEntity value, final ConstraintValidatorContext context) {
                final var startingDate = value.getStartingDate();
                final var endingDate = value.getEndingDate();
                if (isEndDateRequired) {
                        return startingDate.isBefore(endingDate);
                }
                return endingDate == null || startingDate.isBefore(endingDate);
        }

}
