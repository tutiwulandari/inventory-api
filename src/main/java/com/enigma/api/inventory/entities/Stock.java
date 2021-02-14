package com.enigma.api.inventory.entities;

import javax.persistence.*;

@Table(name="stock")
@Entity
public class Stock extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer quantity;


    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


    public Stock() {
    }

    public Stock(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
