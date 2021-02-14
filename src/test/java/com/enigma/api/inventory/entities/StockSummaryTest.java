package com.enigma.api.inventory.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StockSummaryTest {

    @Mock
    private Item item;


    @Test
    void setStockSummary() {
        StockSummary expected = new StockSummary();
        expected.setItem(item);
        expected.setQuantity(10);

        StockSummary actual = new StockSummary();
        actual.setItem(item);
        actual.setQuantity(10);

        assertEquals(expected.getItem(), actual.getItem());
        assertEquals(expected.getQuantity(), actual.getQuantity());


    }
}
