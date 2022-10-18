package com.ardecs.carconfiguration.models.mainEntities;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "transmissoin")
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 2, max = 50, message = "Name should be between "
            + "2 and 50 characters")
    @NotEmpty(message = "Name should not be empty")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "trans")
    private Set<TransModelComplect> transModelComplects = new LinkedHashSet<>();

    public Set<TransModelComplect> getTransModelComplects() {
        return transModelComplects;
    }

    public void setTransModelComplects(Set<TransModelComplect> transModelComplects) {
        this.transModelComplects = transModelComplects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
