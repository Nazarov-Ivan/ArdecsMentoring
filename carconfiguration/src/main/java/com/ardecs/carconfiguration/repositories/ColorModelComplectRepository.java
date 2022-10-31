package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.ColorModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorModelComplectRepository extends JpaRepository<ColorModelComplect, Long> {

    Optional<ColorModelComplect> findByColorIdAndModelComplectationColor(Long colorId, ModelComplectation modelComplectation);

    void deleteAllByModelComplectationColor(ModelComplectation modelComplectation);

    Optional<List<ColorModelComplect>> findAllByModelComplectationColor(ModelComplectation modelComplectation);
}