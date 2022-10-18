package com.ardecs.carconfiguration.models.mainEntities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "color_model_complect")
public class ColorModelComplect {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "model_id", referencedColumnName = "model_id"),
            @JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
    })
    private ModelComplectation modelComplectationColor;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelComplectation getModelComplectationColor() {
        return modelComplectationColor;
    }

    public void setModelComplectationColor(ModelComplectation modelComplectation) {
        this.modelComplectationColor = modelComplectation;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
