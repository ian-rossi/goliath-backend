package br.com.senai.goliath.subject.model;

import br.com.senai.goliath.curriculum.model.Curriculum;
import br.com.senai.goliath.model.NamedEntity;
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
public class Subject extends NamedEntity {

    @ManyToOne
    @Column(nullable = false)
    Curriculum curriculum;

    @Column(nullable = false)
    Integer lessonMinutesPerTerm;

}
