package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.EngineModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EngineModelComplectRepository extends JpaRepository<EngineModelComplect, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into engine_model_complect\n"
            + "    (engine_id, model_id, comp_id, price)\n"
            + "VALUES (:engineId, :modelId, :compId, :price)\n"
            + "on duplicate KEY UPDATE price = :price",
            nativeQuery = true)
    void addEngine(Long engineId, Long modelId, Long compId, int price);

    void deleteByModelComplectationEngine(ModelComplectation modelComplectation);

    Optional<EngineModelComplect> findByModelComplectationEngine(ModelComplectation modelComplectation);
}