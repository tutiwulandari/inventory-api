package com.enigma.api.inventory.models;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ItemGetterSetterTest {


    private ItemElement element;
    private ItemSearch search;
    private  ItemRequest request;
    private UnitModel model;

    @Test
    void ItemElementGetSet() {
        ItemElement expected = new ItemElement();
        expected.setId(1);
        expected.setName("name");
        expected.setPrice(1000);

        ItemElement actual = new ItemElement();
        actual.setId(1);
        actual.setName("name");
        actual.setPrice(1000);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getPrice(),actual.getPrice());
    }

    @Test
    void ItemSearchGetSet() {
        ItemSearch expected = new ItemSearch();
        expected.setId(1);
        expected.setName("name");
        expected.setPrice(1000.0);

        ItemSearch actual = new ItemSearch();
        actual.setId(1);
        actual.setName("name");
        actual.setPrice(1000.0);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getPrice(),actual.getPrice());

    }

    @Test
    void ItemRequestGetSet() {
        ItemRequest expected = new ItemRequest();
        expected.setName("name");
        expected.setPrice(1000.0);
        expected.setUnitId(1);

        ItemRequest actual = new ItemRequest();
        actual.setName("name");
        actual.setPrice(1000.0);
        actual.setUnitId(1);

        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getPrice(),actual.getPrice());
        assertEquals(expected.getUnitId(),actual.getUnitId());

    }
    @Test
    void ItemResponseGetSet() {
        ItemResponse expected = new ItemResponse();
        expected.setId(1);
        expected.setName("name");
        expected.setPrice(1000.0);
        expected.setUnit(model);

        ItemResponse actual = new ItemResponse();
        actual.setId(1);
        actual.setName("name");
        actual.setPrice(1000.0);
        actual.setUnit(model);

        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getPrice(),actual.getPrice());
        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getUnit(),actual.getUnit());

    }

}
