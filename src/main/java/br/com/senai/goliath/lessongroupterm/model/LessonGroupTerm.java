package br.com.senai.goliath.lessongroupterm.model;

import br.com.senai.goliath.curriculum.model.Curriculum;
import br.com.senai.goliath.lessongroup.model.LessonGroup;
import br.com.senai.goliath.lessongroupterm.annotations.SubjectLessonHoursMustFitTermInterval;
import br.com.senai.goliath.model.SuperEntity;
import br.com.senai.goliath.term.model.Term;
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
@SubjectLessonHoursMustFitTermInterval
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_lesson_group_term", columnNames = { "lesson_group", "term" }))
public class LessonGroupTerm extends SuperEntity {

    @ManyToOne
    @Column(nullable = false)
    LessonGroup lessonGroup;

    @ManyToOne
    @Column(nullable = false)
    Term term;

    @ManyToOne
    @Column(nullable = false)
    Curriculum curriculum;

}
