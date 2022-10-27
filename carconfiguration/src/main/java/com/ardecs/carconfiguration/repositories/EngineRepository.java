package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {

    Optional<Engine> findByName(String name);
}