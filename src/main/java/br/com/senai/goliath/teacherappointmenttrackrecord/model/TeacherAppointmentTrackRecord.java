package br.com.senai.goliath.teacherappointmenttrackrecord.model;

import java.time.LocalDate;

import br.com.senai.goliath.appointmenttable.model.AppointmentTable;
import br.com.senai.goliath.model.SuperEntity;
import br.com.senai.goliath.teacher.model.Teacher;
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
@EndDateAndStartDateMustNotOverlap
@EndDateMustBeGreaterThanStartDate
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherAppointmentTrackRecord extends TrackRecordEntity {

    @ManyToOne
    @Column(nullable = false)
    Teacher teacher;

    @ManyToOne
    @Column(nullable = false)
    AppointmentTable appointmentTable;

    LocalDate endingDate;

    @Override
    public SuperEntity getMainEntity() {
        return teacher;
    }

    @Override
    public String getMainEntityFieldName() {
        return "teacher";
    }

}
