package com.enigma.api.inventory.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StockTest {

    @Mock
    private Item item;

    @Test
    void setItemEntity() {
        Stock expected = new Stock();
        expected.setId(1);
        expected.setQuantity(100);
        expected.setItem(item);

        Stock actual = new Stock();
        actual.setId(1);
        actual.setQuantity(100);
        actual.setItem(item);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getItem(), actual.getItem());
    }


}
