package com.ardecs.carconfiguration.models.mainEntities;

import javax.persistence.FetchType;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "model_complectation")
public class ModelComplectation {

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

    @OneToMany(mappedBy = "modelComplectationTrans")
    private Set<TransModelComplect> transModelComplects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "modelComplectationAccessory")
    private Set<AccessoryModelComplect> accessoryModelComplects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "modelComplectationColor")
    private Set<ColorModelComplect> colorModelComplects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "modelComplectationEngine")
    private Set<EngineModelComplect> engineModelComplects = new LinkedHashSet<>();

    public Set<EngineModelComplect> getEngineModelComplects() {
        return engineModelComplects;
    }

    public void setEngineModelComplects(Set<EngineModelComplect> engineModelComplects) {
        this.engineModelComplects = engineModelComplects;
    }

    public Set<ColorModelComplect> getColorModelComplects() {
        return colorModelComplects;
    }

    public void setColorModelComplects(Set<ColorModelComplect> colorModelComplects) {
        this.colorModelComplects = colorModelComplects;
    }

    public Set<AccessoryModelComplect> getAccessoryModelComplects() {
        return accessoryModelComplects;
    }

    public void setAccessoryModelComplects(Set<AccessoryModelComplect> accessoryModelComplects) {
        this.accessoryModelComplects = accessoryModelComplects;
    }

    public Set<TransModelComplect> getTransModelComplects() {
        return transModelComplects;
    }

    public void setTransModelComplects(Set<TransModelComplect> transModelComplects) {
        this.transModelComplects = transModelComplects;
    }

    public Complectation getComp() {
        return comp;
    }

    public void setComp(Complectation comp) {
        this.comp = comp;
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
