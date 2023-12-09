package br.com.senai.goliath.repository;

import java.time.LocalTime;

import org.hibernate.query.sqm.NodeBuilder;
import org.hibernate.query.sqm.tree.expression.SqmExpression;
import org.hibernate.query.sqm.tree.from.SqmRoot;

import br.com.senai.goliath.model.SuperEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Expression;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EntityManagerDelegate {

    private static final int MINUTES_IN_A_HOUR = 60;

    @Delegate
    EntityManager entityManager;

    @Getter
    NodeBuilder nodeBuilder;

    public EntityManagerDelegate(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.nodeBuilder = (NodeBuilder) entityManager.getCriteriaBuilder();
    }

    public Expression<Number> totalMinutes(final SqmExpression<LocalTime> timePath) {
        return nodeBuilder.sum(
            nodeBuilder.prod(
                nodeBuilder.hour(timePath),
                MINUTES_IN_A_HOUR
            ),
            nodeBuilder.minute(timePath)
        );
    }

    public <T extends SuperEntity> SqmRoot<T> getEntityRoot(final Class<T> entityClass) {
        return new SqmRoot<>(
            nodeBuilder.getDomainModel().entity(entityClass),
            null,
            false,
            nodeBuilder
        );
    }

}
