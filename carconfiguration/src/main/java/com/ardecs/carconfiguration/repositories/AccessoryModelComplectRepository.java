package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.AccessoryModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessoryModelComplectRepository extends JpaRepository<AccessoryModelComplect, Long> {

    @Transactional
    @Modifying
    @Query(value = "insert into accessory_model_complect\n"
            + "    (access_id, model_id, comp_id, price)\n"
            + "VALUES (:accessId, :modelId, :compId, :price)\n"
            + "on duplicate KEY UPDATE price = :price",
            nativeQuery = true)
    void addAccessory(Long accessId, Long modelId, Long compId, int price);

    Optional<AccessoryModelComplect> findByAccessoryIdAndModelComplectationAccessory(Long accessId,
                                                                                      ModelComplectation modelComplectation);

    void deleteAllByModelComplectationAccessory(ModelComplectation modelComplectation);

    Optional<List<AccessoryModelComplect>> findAllByModelComplectationAccessory(ModelComplectation modelComplectation);

    @Transactional
    @Modifying
    @Query(value = "delete\n"
            + "from accessory_model_complect\n"
            + "where access_id = :access_id\n"
            + "  and model_id = :model_id\n"
            + "  and comp_id = :comp_id",
            nativeQuery = true)
    void delete(@Param(value = "access_id") Long accessId,
                @Param(value = "model_id") Long modelId,
                @Param(value = "comp_id") Long compId);
}