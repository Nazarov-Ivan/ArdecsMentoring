package com.ardecs.carconfiguration.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accessory_model_complect")
public class AccessoryModelComplect extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "access_id", nullable = false)
    private Accessory accessory;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "model_id", referencedColumnName = "model_id"),
            @JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
    })
    private ModelComplectation modelComplectationAccessory;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ModelComplectation getModelComplectationAccessory() {
        return modelComplectationAccessory;
    }

    public void setModelComplectationAccessory(ModelComplectation modelComplectation) {
        this.modelComplectationAccessory = modelComplectation;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory access) {
        this.accessory = access;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
