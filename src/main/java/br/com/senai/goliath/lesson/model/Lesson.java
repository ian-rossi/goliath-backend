package br.com.senai.goliath.lesson.model;

import java.time.LocalDate;

import br.com.senai.goliath.lessongroupterm.model.LessonGroupTerm;
import br.com.senai.goliath.teachersubject.model.TeacherSubject;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson extends TimeSpanEntity {

    @ManyToOne
    @Column(nullable = false)
    LessonGroupTerm lessonGroupTerm;

    @ManyToOne
    @Column(nullable = false)
    TeacherSubject teacherSubject;

    @Column(nullable = false)
    LocalDate date;

}
