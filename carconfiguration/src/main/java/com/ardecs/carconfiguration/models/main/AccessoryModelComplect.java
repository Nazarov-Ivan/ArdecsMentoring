package com.ardecs.carconfiguration.models.main;

import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accessory_model_complect")
public class AccessoryModelComplect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "access_id", nullable = false)
    private Accessory access;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ModelComplectation modelComplectationAccessory;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

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

    public Accessory getAccess() {
        return access;
    }

    public void setAccess(Accessory access) {
        this.access = access;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
