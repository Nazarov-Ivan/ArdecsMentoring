package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.ColorModelComplect;
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
public interface ColorModelComplectRepository extends JpaRepository<ColorModelComplect, Long> {

    @Transactional
    @Modifying
    @Query(value = "delete\n"
            + "FROM color_model_complect\n"
            + "where color_id = :color_id\n"
            + "  and model_id = :model_id\n"
            + "  and comp_id = :comp_id",
            nativeQuery = true)
    void delete(@Param(value = "color_id") Long colorId,
                @Param(value = "model_id") Long modelId,
                @Param(value = "comp_id") Long compId);

    @Transactional
    @Modifying
    @Query(value = "insert into color_model_complect\n"
            + "    (color_id, model_id, comp_id, price)\n"
            + "VALUES (:colorId, :modelId, :compId, :price)\n"
            + "on duplicate KEY UPDATE price = :price",
            nativeQuery = true)
    void addColor(Long colorId, Long modelId, Long compId, int price);

    Optional<ColorModelComplect> findByColorIdAndModelComplectationColor(Long colorId, ModelComplectation modelComplectation);

    void deleteAllByModelComplectationColor(ModelComplectation modelComplectation);

    Optional<List<ColorModelComplect>> findAllByModelComplectationColor(ModelComplectation modelComplectation);
}