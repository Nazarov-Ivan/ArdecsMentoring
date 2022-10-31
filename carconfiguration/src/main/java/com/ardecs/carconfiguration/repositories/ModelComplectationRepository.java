package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.models.entities.ModelComplectationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelComplectationRepository extends JpaRepository<ModelComplectation, ModelComplectationId> {
}