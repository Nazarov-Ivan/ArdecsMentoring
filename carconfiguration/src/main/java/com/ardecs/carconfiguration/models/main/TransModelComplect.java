package com.ardecs.carconfiguration.models.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "trans_model_complect")
public class TransModelComplect {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trans_id")
    private Transmission trans;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ModelComplectation modelComplectationTrans;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ModelComplectation getModelComplectationTrans() {
        return modelComplectationTrans;
    }

    public void setModelComplectationTrans(ModelComplectation modelComplectation1) {
        this.modelComplectationTrans = modelComplectation1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transmission getTrans() {
        return trans;
    }

    public void setTrans(Transmission trans) {
        this.trans = trans;
    }


}
