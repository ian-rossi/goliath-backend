package br.com.senai.goliath.trackrecord.validator;

import java.math.BigInteger;
import java.util.ArrayList;

import br.com.senai.goliath.repository.RepositoriesWrapper;
import br.com.senai.goliath.trackrecord.annotations.EndDateAndStartDateMustNotOverlap;
import br.com.senai.goliath.trackrecord.model.TrackRecordEntity;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EndDateAndStartDateMustNotOverlapValidator implements ConstraintValidator<EndDateAndStartDateMustNotOverlap, TrackRecordEntity> {

        final RepositoriesWrapper repositoriesWrapper;
        boolean isEndDateRequired;

        @Override
        public void initialize(final EndDateAndStartDateMustNotOverlap constraintAnnotation) {
                this.isEndDateRequired = constraintAnnotation.isEndDateRequired();
        }

        @Override
        public boolean isValid(final TrackRecordEntity value, final ConstraintValidatorContext context) {
                final var entityClass = value.getClass();
                final var repository = repositoriesWrapper.getRepositoryFor(entityClass);
                final var mainEntity = value.getMainEntity();
                final var mainEntityFieldName = value.getMainEntityFieldName();
                return repository.exists((root, query, criteriaBuilder) -> {
                        final var predicates = new ArrayList<Predicate>(
                                BigInteger.TWO.add(BigInteger.ONE).intValue()
                        );
                        predicates.add(
                                criteriaBuilder.equal(
                                        root.get(mainEntityFieldName),
                                        mainEntity
                                )
                        );
                        final var endingDateLoeStartingDate = criteriaBuilder.lessThanOrEqualTo(
                                root.get("endingDate"), value.getStartingDate()
                        );
                        final var startingDateGoeEndingDate = criteriaBuilder.greaterThanOrEqualTo(
                                root.get("startingDate"), value.getEndingDate()
                        );
                        if (isEndDateRequired) {
                                predicates.add(endingDateLoeStartingDate);
                                predicates.add(startingDateGoeEndingDate);
                        } else {
                                final var dbEndingDateIsNull =
                                        criteriaBuilder.isNull(root.get("endingDate"));
                                predicates.add(
                                        criteriaBuilder.or(
                                                dbEndingDateIsNull,
                                                endingDateLoeStartingDate
                                        )
                                );
                                if (value.getEndingDate() != null) {
                                        predicates.add(
                                                criteriaBuilder
                                                        .or(startingDateGoeEndingDate)
                                        );
                                }

                        }
                        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                });
        }

}
