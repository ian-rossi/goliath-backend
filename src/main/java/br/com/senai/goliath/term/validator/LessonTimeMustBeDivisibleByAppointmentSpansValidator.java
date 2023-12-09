package br.com.senai.goliath.term.validator;

import java.math.BigInteger;
import java.time.LocalTime;

import br.com.senai.goliath.appointmentspan.model.AppointmentSpan;
import br.com.senai.goliath.lessongroupterm.annotations.SubjectLessonHoursMustFitTermInterval;
import br.com.senai.goliath.repository.EntityManagerDelegate;
import br.com.senai.goliath.term.model.Term;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonTimeMustBeDivisibleByAppointmentSpansValidator implements ConstraintValidator<SubjectLessonHoursMustFitTermInterval, Term> {

        EntityManagerDelegate entityManager;

        @Override
        public boolean isValid(final Term value, final ConstraintValidatorContext context) {
                final var criteriaBuilder = entityManager.getNodeBuilder();
                final var query = criteriaBuilder.createQuery(boolean.class);
                final var root = query.select(criteriaBuilder.literal(true))
                        .from(AppointmentSpan.class);
                final var appointmentTableEq = criteriaBuilder.equal(
                        root.get("appointmentTable"),
                        value.getAppointmentTable()
                );
                final var totalMinutesFromStartingTime = entityManager.totalMinutes(
                        root.get("startingTime").as(LocalTime.class)
                );
                final var totalMinutesFromEndingTime = entityManager.totalMinutes(
                        root.get("endingTime").as(LocalTime.class)
                );
                final var diffBetweenEndingAndStartingTime = criteriaBuilder.diff(
                        totalMinutesFromEndingTime,
                        totalMinutesFromStartingTime
                ).as(Integer.class);
                final var modBetweenDiffAndLessonTime = criteriaBuilder.mod(
                        diffBetweenEndingAndStartingTime,
                        value.getLessonTimeInMinutes().intValue()
                );
                final var modIsZero = criteriaBuilder.equal(modBetweenDiffAndLessonTime, BigInteger.ZERO.intValue());
                return entityManager.createQuery(query.where(appointmentTableEq).having(modIsZero))
                        .setMaxResults(BigInteger.ONE.intValue())
                        .getResultStream()
                        .findFirst()
                        .isPresent();
        }
}
