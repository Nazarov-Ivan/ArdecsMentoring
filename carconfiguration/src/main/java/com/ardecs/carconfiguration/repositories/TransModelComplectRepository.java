package com.ardecs.carconfiguration.repositories;

import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.models.entities.TransModelComplect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TransModelComplectRepository extends JpaRepository<TransModelComplect, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into trans_model_complect\n"
            + "    (trans_id, model_id, comp_id, price)\n"
            + "VALUES (:transId, :modelId, :compId, :price)\n"
            + "on duplicate KEY UPDATE price = :price",
            nativeQuery = true)
    void addTransmission(Long transId, Long modelId, Long compId, int price);

    void deleteByModelComplectationTrans(ModelComplectation modelComplectation);

    Optional<TransModelComplect> findByModelComplectationTrans(ModelComplectation modelComplectation);
}