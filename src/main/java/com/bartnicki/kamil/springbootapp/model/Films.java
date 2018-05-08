package com.bartnicki.kamil.springbootapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Films {

    private Long Id;
    private String name;
    private String description;
    private Integer productionYear;

    public Films() {
    }

    public Films(String name, String description, Integer productionYear) {
        this.name = name;
        this.description = description;
        this.productionYear = productionYear;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
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

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }


}
