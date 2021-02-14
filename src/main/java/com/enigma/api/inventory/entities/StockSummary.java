package com.enigma.api.inventory.entities;

import com.enigma.api.inventory.entities.Item;

public class StockSummary {
    private Item item;
    private Integer quantity;

    public StockSummary() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
