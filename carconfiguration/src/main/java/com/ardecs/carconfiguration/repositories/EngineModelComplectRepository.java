package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.EngineModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EngineModelComplectRepository extends JpaRepository<EngineModelComplect, Long> {

    void deleteByModelComplectationEngine(ModelComplectation modelComplectation);

    Optional<EngineModelComplect> findByModelComplectationEngine(ModelComplectation modelComplectation);
}