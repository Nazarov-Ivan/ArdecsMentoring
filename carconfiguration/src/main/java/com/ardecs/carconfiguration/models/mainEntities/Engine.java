package com.ardecs.carconfiguration.models.mainEntities;

import javax.persistence.GenerationType;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "description")
    private String description;

    @Size(min = 2, max = 50, message = "Name should be between "
            + "2 and 50 characters")
    @NotEmpty(message = "Name should not be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "power", nullable = false)
    private Integer power;

    @OneToMany(mappedBy = "engine")
    private Set<EngineModelComplect> engineModelComplects = new LinkedHashSet<>();

    public Set<EngineModelComplect> getEngineModelComplects() {
        return engineModelComplects;
    }

    public void setEngineModelComplects(Set<EngineModelComplect> engineModelComplects) {
        this.engineModelComplects = engineModelComplects;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
