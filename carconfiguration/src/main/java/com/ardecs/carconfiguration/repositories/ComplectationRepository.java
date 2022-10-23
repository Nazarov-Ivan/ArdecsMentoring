package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.Complectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplectationRepository extends JpaRepository<Complectation, Long> {
}