package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {

    Optional<Accessory> findByName(String Name);
}