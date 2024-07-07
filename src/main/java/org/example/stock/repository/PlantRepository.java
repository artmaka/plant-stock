package org.example.stock.repository;

import org.example.stock.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для выполнения операций с сущностью Plant в базе данных.
 */

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>, JpaSpecificationExecutor<Plant> {
}
