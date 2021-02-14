package com.enigma.api.inventory.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ItemTest {

    @Mock
    private List<Stock> stocks;

    @Mock
    private Unit unit;

    @Test
    void setItemEntity() {
        Item expected = new Item();
        expected.setId(1);
        expected.setName("name");
        expected.setPrice(1000.0);
        expected.setUnit(unit);
        expected.setStocks(stocks);

        Item actual = new Item();
        actual.setId(1);
        actual.setName("name");
        actual.setPrice(1000.0);
        actual.setUnit(unit);
        actual.setStocks(stocks);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getUnit(), actual.getUnit());
        assertEquals(expected.getStocks(), actual.getStocks());
    }
    @Test
    void setItem() {
        Item expected = new Item(1, "name",1000.0);

        Item actual = new Item(1,"name", 1000.0);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPrice(), actual.getPrice());
    }
}
