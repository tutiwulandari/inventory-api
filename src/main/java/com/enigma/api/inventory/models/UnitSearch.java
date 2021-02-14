package com.enigma.api.inventory.models;
import com.enigma.api.inventory.models.PageSearch;
import com.enigma.api.inventory.models.validations.Alphabetic;

public class UnitSearch extends PageSearch {

    @Alphabetic
    private String code;

    private String description;

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
}
