package com.enigma.api.inventory.models;

import com.enigma.api.inventory.models.ItemElement;

public class StockElement {
    private Integer id;
    private ItemElement item;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemElement getItem() {
        return item;
    }

    public void setItem(ItemElement item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
