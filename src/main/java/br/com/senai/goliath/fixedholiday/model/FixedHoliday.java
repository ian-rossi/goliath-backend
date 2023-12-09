package br.com.senai.goliath.fixedholiday.model;

import java.time.MonthDay;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.Type;

import br.com.senai.goliath.model.NamedEntity;
import io.hypersistence.utils.hibernate.type.basic.MonthDayIntegerType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Check(
    constraints = """
        month_day BETWEEN 101 AND 131 OR
        month_day BETWEEN 201 AND 229 OR
        month_day BETWEEN 301 AND 331 OR
        month_day BETWEEN 401 AND 430 OR
        month_day BETWEEN 501 AND 531 OR
        month_day BETWEEN 601 AND 630 OR
        month_day BETWEEN 701 AND 731 OR
        month_day BETWEEN 801 AND 831 OR
        month_day BETWEEN 901 AND 930 OR
        month_day BETWEEN 1001 AND 1031 OR
        month_day BETWEEN 1101 AND 1130 OR
        month_day BETWEEN 1201 AND 1231
    """
)
public class FixedHoliday extends NamedEntity {

    @Type(MonthDayIntegerType.class)
    @Column(nullable = false, unique = true)
    MonthDay monthDay;

}
