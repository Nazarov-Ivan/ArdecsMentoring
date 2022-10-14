package com.ardecs.carconfiguration.models.main;

import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;


@Embeddable
public class ModelComplectationId implements Serializable {
    private static final long serialVersionUID = 2518103565153065128L;
    @NotNull
    private Long modelId;

    @NotNull
    private Long compId;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ModelComplectationId entity = (ModelComplectationId) o;
        return Objects.equals(this.modelId, entity.modelId) &&
                Objects.equals(this.compId, entity.compId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, compId);
    }

}