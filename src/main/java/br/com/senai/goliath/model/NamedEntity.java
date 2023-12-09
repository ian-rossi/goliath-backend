package br.com.senai.goliath.model;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Check(constraints = "NULLIF(LTRIM(RTRIM(name)), '') IS NOT NULL AND LENGTH(name) BETWEEN 2 AND 64")
public class NamedEntity extends SuperEntity {

    @Size(min = 2, max = 64)
    @Column(nullable = false, unique = true, length = 64)
    String name;

}
