package br.com.senai.goliath.teachersubject.model;

import br.com.senai.goliath.model.SuperEntity;
import br.com.senai.goliath.subject.model.Subject;
import br.com.senai.goliath.teacher.model.Teacher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_teacher_subject", columnNames = { "teacher", "subject" }))
public class TeacherSubject extends SuperEntity {

    @ManyToOne
    @Column(nullable = false)
    Teacher teacher;

    @ManyToOne
    @Column(nullable = false)
    Subject subject;

}
