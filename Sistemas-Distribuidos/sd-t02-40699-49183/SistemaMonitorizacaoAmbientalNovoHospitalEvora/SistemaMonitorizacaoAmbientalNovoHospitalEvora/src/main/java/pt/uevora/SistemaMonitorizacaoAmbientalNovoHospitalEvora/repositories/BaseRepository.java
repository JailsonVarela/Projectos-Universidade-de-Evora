package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository <EntityType, IdType> extends Repository <EntityType, IdType> {
    Optional<EntityType> findById(IdType id);
    EntityType save(EntityType entity);
    List<EntityType> findAll();
    void delete(EntityType entityType);
}
