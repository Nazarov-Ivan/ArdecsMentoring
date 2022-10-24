package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    Optional<Color> findByName(String Name);
}