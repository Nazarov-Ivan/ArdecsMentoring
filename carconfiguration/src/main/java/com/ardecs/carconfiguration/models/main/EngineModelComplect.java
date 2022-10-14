package com.ardecs.carconfiguration.models.main;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "engine_model_complect")
public class EngineModelComplect {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "model_id", referencedColumnName = "model_id"),
            @JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
    })
    private ModelComplectation modelComplectationEngine;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ModelComplectation getModelComplectationEngine() {
        return modelComplectationEngine;
    }

    public void setModelComplectationEngine(ModelComplectation modelComplectation) {
        this.modelComplectationEngine = modelComplectation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
