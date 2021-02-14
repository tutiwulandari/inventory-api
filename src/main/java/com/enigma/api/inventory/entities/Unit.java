package com.enigma.api.inventory.entities;

import javax.persistence.*;
import java.util.List;

@Table(name="unit")
@Entity
public class  Unit extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String code;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    private List<Item> items;


    public Unit(Integer id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public Unit() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


}

