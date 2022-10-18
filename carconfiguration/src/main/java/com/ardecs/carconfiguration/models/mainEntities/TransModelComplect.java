package com.ardecs.carconfiguration.models.mainEntities;

import javax.persistence.*;
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
    @JoinColumns({
            @JoinColumn(name = "model_id", referencedColumnName = "model_id"),
            @JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
    })
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
