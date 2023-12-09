package br.com.senai.goliath.appointmentspan.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class BrazilianArticle319CLTValidatorTest {

    @Spy
    BrazilianArticle319CLTValidator article319cltValidator;

    @Nested
    class IsValidTest {

        @ParameterizedTest
        @EnumSource(value = DayOfWeek.class, mode = EnumSource.Mode.EXCLUDE, names = "SUNDAY")
        void testValidDaysOfWeek(final DayOfWeek dayOfWeek) {
            assertTrue(article319cltValidator.isValid(dayOfWeek, null));
        }

        @Test
        void testWhenDayOfWeekIsSundayShouldReturnFalse() {
            assertFalse(article319cltValidator.isValid(DayOfWeek.SUNDAY, null));
        }

    }

}
