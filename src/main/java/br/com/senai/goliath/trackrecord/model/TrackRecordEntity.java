package br.com.senai.goliath.trackrecord.model;

import java.time.LocalDate;

import org.hibernate.annotations.Check;

import br.com.senai.goliath.model.SuperEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Check(constraints = "ending_date IS NULL OR starting_date < ending_date")
public abstract class TrackRecordEntity extends SuperEntity {

    @Column(nullable = false)
    LocalDate startingDate;

    @Nullable
    public abstract LocalDate getEndingDate();

    @NonNull
    public abstract SuperEntity getMainEntity();

    @NonNull
    public abstract String getMainEntityFieldName();
}
