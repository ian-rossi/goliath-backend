package br.com.senai.goliath.term.model;

import java.time.LocalDate;

import br.com.senai.goliath.appointmenttable.model.AppointmentTable;
import br.com.senai.goliath.model.SuperEntity;
import br.com.senai.goliath.trackrecord.annotations.EndDateAndStartDateMustNotOverlap;
import br.com.senai.goliath.trackrecord.annotations.EndDateMustBeGreaterThanStartDate;
import br.com.senai.goliath.trackrecord.model.TrackRecordEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
@EndDateAndStartDateMustNotOverlap(isEndDateRequired = true)
@EndDateMustBeGreaterThanStartDate(isEndDateRequired = true)
public class Term extends TrackRecordEntity {

    @Column(nullable = false)
    LocalDate endingDate;

    @ManyToOne
    @Column(nullable = false)
    AppointmentTable appointmentTable;

    @Column(nullable = false)
    Short lessonTimeInMinutes;

    @Override
    public SuperEntity getMainEntity() {
        return appointmentTable;
    }

    @Override
    public String getMainEntityFieldName() {
        return "appointmentTable";
    }

}
