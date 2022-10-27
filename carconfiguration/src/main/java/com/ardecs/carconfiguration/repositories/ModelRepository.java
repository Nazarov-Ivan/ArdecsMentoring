package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.Brand;
import com.ardecs.carconfiguration.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Optional<Model> findByName(String name);

    List<Model> findAllByBrand(Brand brand);
}