package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.AccessoryModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessoryModelComplectRepository extends JpaRepository<AccessoryModelComplect, Long> {

    Optional<AccessoryModelComplect> findByAccessoryIdAndModelComplectationAccessory(Long accessId,
                                                                                      ModelComplectation modelComplectation);

    void deleteAllByModelComplectationAccessory(ModelComplectation modelComplectation);

    Optional<List<AccessoryModelComplect>> findAllByModelComplectationAccessory(ModelComplectation modelComplectation);
}