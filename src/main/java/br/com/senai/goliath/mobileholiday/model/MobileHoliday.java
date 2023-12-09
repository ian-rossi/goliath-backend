package br.com.senai.goliath.mobileholiday.model;

import java.time.LocalDate;

import br.com.senai.goliath.model.NamedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_mobile_holiday_date", columnNames = "date"))
public class MobileHoliday extends NamedEntity {

    @Column(nullable = false, unique = true)
    LocalDate date;

}
