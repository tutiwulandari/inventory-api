package com.enigma.api.inventory.models;

import com.enigma.api.inventory.models.PageSearch;

public class StockSearch extends PageSearch {
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
