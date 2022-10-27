package com.ardecs.carconfiguration.models.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "model_complectation")
public class ModelComplectation extends AbstractEntity {

    @EmbeddedId
    private ModelComplectationId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(value = "modelId")
    @JoinColumn(name = "model_id", referencedColumnName = "id", nullable = false)
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(value = "compId")
    @JoinColumn(name = "comp_id", referencedColumnName = "id", nullable = false)
    private Complectation comp;

    @Transient
    private EngineModelComplect engineModelComplect;

    @Transient
    private TransModelComplect transModelComplect;

    @Transient
    private List<AccessoryModelComplect> accessoryModelComplects;

    @Transient
    private List<ColorModelComplect> colorModelComplects;

    public EngineModelComplect getEngineModelComplect() {
        return engineModelComplect;
    }

    public void setEngineModelComplect(EngineModelComplect engineModelComplect) {
        this.engineModelComplect = engineModelComplect;
    }

    public List<ColorModelComplect> getColorModelComplects() {
        return colorModelComplects;
    }

    public void setColorModelComplects(List<ColorModelComplect> colorModelComplects) {
        this.colorModelComplects = colorModelComplects;
    }

    public List<AccessoryModelComplect> getAccessoryModelComplects() {
        return accessoryModelComplects;
    }

    public void setAccessoryModelComplects(List<AccessoryModelComplect> accessoryModelComplects) {
        this.accessoryModelComplects = accessoryModelComplects;
    }

    public TransModelComplect getTransModelComplect() {
        return transModelComplect;
    }

    public void setTransModelComplect(TransModelComplect transModelComplect) {
        this.transModelComplect = transModelComplect;
    }

    public Complectation getComp() {
        return comp;
    }

    public void setComp(Complectation complectation) {
        this.comp = complectation;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public ModelComplectationId getId() {
        return id;
    }

    public void setId(ModelComplectationId id) {
        this.id = id;
    }
}
