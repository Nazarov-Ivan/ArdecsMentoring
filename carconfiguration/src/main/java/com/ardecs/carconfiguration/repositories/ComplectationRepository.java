package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.Complectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplectationRepository extends JpaRepository<Complectation, Long> {
    Optional<Complectation> findByName(String name);

    @Query(value = "select c.id, c.name from complectation c\n"
            + "inner JOIN model_complectation mc on c.id = mc.comp_id where model_id = :modelId",
            nativeQuery = true)
    List<Complectation> getCompByIdOfModel(@Param("modelId") Long modelId);
}