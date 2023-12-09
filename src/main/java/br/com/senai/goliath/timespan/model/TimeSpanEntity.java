package br.com.senai.goliath.timespan.model;

import java.time.LocalTime;

import org.hibernate.annotations.Check;

import br.com.senai.goliath.model.SuperEntity;
import br.com.senai.goliath.timespan.annotations.EndTimeMustBeGreaterThanStartTime;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@EndTimeMustBeGreaterThanStartTime
@FieldDefaults(level = AccessLevel.PRIVATE)
@Check(constraints = "starting_time < ending_time")
public abstract class TimeSpanEntity extends SuperEntity {

    @Column(nullable = false)
    LocalTime startingTime;

    @Column(nullable = false)
    LocalTime endingTime;

}
