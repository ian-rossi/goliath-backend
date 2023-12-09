package br.com.senai.goliath.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.support.Repositories;

import br.com.senai.goliath.model.SuperEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RepositoriesWrapper {

    Repositories repositories;

    @SuppressWarnings("unchecked")
    public <T extends SuperEntity> JpaSpecificationExecutor<T> getRepositoryFor(final Class<T> entityClass) {
        return repositories.getRepositoryFor(entityClass)
            .map(repository -> (JpaSpecificationExecutor<T>) repository)
            .orElseThrow(() -> {
                final var message = "JPA Repository not found for entity class " + entityClass.getName();
                throw new IllegalStateException(message);
            });
    }
}
