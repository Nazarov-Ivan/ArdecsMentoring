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
@Table(name = "Accessory")
public class Accessory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between "
            + "2 and 50 characters")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "accessory")
    private Set<AccessoryModelComplect> accessoryModelComplects
            = new LinkedHashSet<>();

    public Set<AccessoryModelComplect> getAccessoryModelComplects() {
        return accessoryModelComplects;
    }

    public void setAccessoryModelComplects(
           Set<AccessoryModelComplect> accessoryModelComplects) {
        this.accessoryModelComplects = accessoryModelComplects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
