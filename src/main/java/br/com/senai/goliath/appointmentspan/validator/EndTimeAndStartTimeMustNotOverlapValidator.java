package br.com.senai.goliath.appointmentspan.validator;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.senai.goliath.appointmentspan.annotations.EndTimeAndStartTimeMustNotOverlap;
import br.com.senai.goliath.appointmentspan.model.AppointmentSpan;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EndTimeAndStartTimeMustNotOverlapValidator implements ConstraintValidator<EndTimeAndStartTimeMustNotOverlap, AppointmentSpan> {

    JpaSpecificationExecutor<AppointmentSpan> appointmentSpanRepository;

    @Override
    public boolean isValid(final AppointmentSpan value, final ConstraintValidatorContext context) {
        return !appointmentSpanRepository.exists(
            (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("appointmentTable"), value.getAppointmentTable()),
                criteriaBuilder.equal(root.get("dayOfWeek"), value.getDayOfWeek()),
                criteriaBuilder.greaterThanOrEqualTo(root.get("startingTime"), value.getEndingTime()),
                criteriaBuilder.greaterThanOrEqualTo(root.get("endingTime"), value.getStartingTime())
            )
        );
    }

}
