package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.models.entities.TransModelComplect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransModelComplectRepository extends JpaRepository<TransModelComplect, Long> {

    void deleteByModelComplectationTrans(ModelComplectation modelComplectation);

    Optional<TransModelComplect> findByModelComplectationTrans(ModelComplectation modelComplectation);
}