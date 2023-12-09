package br.com.senai.goliath.appointmentspan.model;

import java.time.DayOfWeek;

import org.hibernate.annotations.Check;

import br.com.senai.goliath.appointmentspan.annotations.BrazilianArticle319CLT;
import br.com.senai.goliath.appointmentspan.annotations.EndTimeAndStartTimeMustNotOverlap;
import br.com.senai.goliath.appointmenttable.model.AppointmentTable;
import br.com.senai.goliath.timespan.model.TimeSpanEntity;
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
@EndTimeAndStartTimeMustNotOverlap
@FieldDefaults(level = AccessLevel.PRIVATE)
@Check(constraints = "day_of_week BETWEEN 0 AND 5")
public class AppointmentSpan extends TimeSpanEntity {

    @ManyToOne
    @Column(nullable = false)
    AppointmentTable appointmentTable;

    @Column(nullable = false)
    @BrazilianArticle319CLT
    DayOfWeek dayOfWeek;

}
