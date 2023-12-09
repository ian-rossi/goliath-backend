package br.com.senai.goliath.lessongroupterm.validator;

import java.math.BigInteger;

import br.com.senai.goliath.lessongroupterm.annotations.SubjectLessonHoursMustFitTermInterval;
import br.com.senai.goliath.lessongroupterm.model.LessonGroupTerm;
import br.com.senai.goliath.repository.EntityManagerDelegate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubjectLessonHoursMustFitTermIntervalValidator implements ConstraintValidator<SubjectLessonHoursMustFitTermInterval, LessonGroupTerm> {

        EntityManagerDelegate entityManager;

        @Override
        public boolean isValid(final LessonGroupTerm value, final ConstraintValidatorContext context) {
                final var queryStr =
                        """
                        WITH term_days AS (
                                SELECT
                                        generate_series(
                                                t.starting_date::TIMESTAMP,
                                                t.ending_date::TIMESTAMP,
                                                '1 day'
                                        )
                                        AS term_day
                                FROM term t
                                WHERE t.id = ?1
                                AND EXTRACT(DOW from term_day) != 0
                        ),
                        WITH term_days_count_grouped_by_day_of_week AS (
                                SELECT
                                        EXTRACT(DOW FROM term_days.term_day) AS day_of_week,
                                        COUNT(term_days.term_day) AS term_days_count
                                FROM term_days td
                                WHERE NOT EXISTS (
                                        SELECT TRUE FROM fixed_holiday fh
                                        WHERE EXTRACT(DAY FROM td.term_day) = FLOOR(fh.month_day / 100)
                                        AND EXTRACT(MONTH FROM td.term_day) = fh.month_day % 100
                                )
                                AND NOT EXISTS (
                                        SELECT TRUE FROM mobile_holiday mh WHERE mh.date = td.term_day
                                )
                                GROUP BY EXTRACT(DOW FROM td.term_day)
                        ),
                        WITH total_hours_on_term AS (
                                SELECT
                                        SUM(tdcgbdow.term_day_count * (a_s.ending_time - a_s.starting_time)) AS total_interval
                                FROM appointment_span a_s
                                INNER JOIN term_days_count_grouped_by_day_of_week tdcgbdow ON tdcgbdow.day_of_week = a_s.day_of_week
                                WHERE a_s.appointment_table = (
                                        SELECT t.appointment_table FROM term t WHERE t.id = ?1
                                )
                        )
                        SELECT
                                SUM(s.lesson_minutes_per_term) - SUM((EXTRACT(HOUR FROM tht.total_interval) * 60) + (EXTRACT(MINUTE FROM tht.total_interval))) = 0
                        FROM subject s, total_hours_on_term tht
                        WHERE s.curriculum = ?3
                        """;
                final var queryResult = entityManager.createNativeQuery(queryStr)
                        .setParameter(1, value.getTerm().getId())
                        .setParameter(2, value.getCurriculum().getId())
                        .setMaxResults(BigInteger.ONE.intValue())
                        .getResultStream().findFirst();
                return queryResult.isPresent() && Boolean.TRUE.equals(queryResult.get());
        }

}
