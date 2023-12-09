package br.com.senai.goliath.trackrecord.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.senai.goliath.teacherappointmenttrackrecord.model.TeacherAppointmentTrackRecord;
import br.com.senai.goliath.trackrecord.annotations.EndDateMustBeGreaterThanStartDate;
import jakarta.validation.Payload;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class EndDateMustBeGreaterThanStartDateValidatorTest {

    private static EndDateMustBeGreaterThanStartDate newEndDateMustBeGreaterThanStartDate(final boolean isEndDateRequired) {
        return new EndDateMustBeGreaterThanStartDate() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String message() {
                return null;
            }

            @Override
            public Class<?>[] groups() {
                return null;
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return null;
            }

            @Override
            public boolean isEndDateRequired() {
                return isEndDateRequired;
            }

        };
    }

    @Nested
    class IsValidEndDateNotRequired {

        @Spy
        EndDateMustBeGreaterThanStartDateValidator endDateMustBeGreaterThanStartDateValidator;

        @BeforeEach
        void beforeEach() {
            endDateMustBeGreaterThanStartDateValidator.initialize(newEndDateMustBeGreaterThanStartDate(false));
        }

        @Test
        void testWhenEndDateIsNotRequiredShouldReturnTrueIfEndingDateIsNull() {
            // Arrange
            final var entity = new TeacherAppointmentTrackRecord();
            entity.setStartingDate(LocalDate.now());

            // Act
            final var isValid = endDateMustBeGreaterThanStartDateValidator.isValid(entity, null);

            // Assert
            assertTrue(isValid);
        }

        @Test
        void testWhenEndDateIsNotRequiredShouldReturnTrueIfStartingDateIsBeforeEndingDate() {
            // Arrange
            final var entity = new TeacherAppointmentTrackRecord();
            entity.setStartingDate(LocalDate.now());
            entity.setEndingDate(LocalDate.now().plusDays(1L));

            // Act
            final var isValid = endDateMustBeGreaterThanStartDateValidator.isValid(entity, null);

            // Assert
            assertTrue(isValid);
        }

        @Test
        void testWhenEndDateIsNotRequiredShouldReturnFalseIfEndingDateIsEqualToStartingDate() {
            // Arrange
            final var entity = new TeacherAppointmentTrackRecord();
            entity.setStartingDate(LocalDate.now());
            entity.setEndingDate(entity.getStartingDate());

            // Act
            final var isValid = endDateMustBeGreaterThanStartDateValidator.isValid(entity, null);

            // Assert
            assertFalse(isValid);
        }

    }

    @Nested
    class IsValidEndDateRequired {

        @Spy
        EndDateMustBeGreaterThanStartDateValidator endDateMustBeGreaterThanStartDateValidator;

        @BeforeEach
        void beforeEach() {
            endDateMustBeGreaterThanStartDateValidator.initialize(newEndDateMustBeGreaterThanStartDate(true));
        }

        @Test
        void testWhenEndDateIsRequiredShouldReturnTrueIfStartingDateIsBeforeEndingDate() {
            // Arrange
            final var entity = new TeacherAppointmentTrackRecord();
            entity.setStartingDate(LocalDate.now());
            entity.setEndingDate(LocalDate.now().plusDays(1L));

            // Act
            final var isValid = endDateMustBeGreaterThanStartDateValidator.isValid(entity, null);

            // Assert
            assertTrue(isValid);
        }

        @Test
        void testWhenEndDateIsRequiredShouldReturnFalseIfEndingDateIsEqualToStartingDate() {
            // Arrange
            final var entity = new TeacherAppointmentTrackRecord();
            entity.setStartingDate(LocalDate.now());
            entity.setEndingDate(entity.getStartingDate());

            // Act
            final var isValid = endDateMustBeGreaterThanStartDateValidator.isValid(entity, null);

            // Assert
            assertFalse(isValid);
        }

    }

}
