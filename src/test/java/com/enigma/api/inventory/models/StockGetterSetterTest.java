package com.enigma.api.inventory.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StockGetterSetterTest {

    private StockElement element;
    private StockRequest request;
    private StockResponse response;
    private StockSearch search;
    private ItemElement item;
    private ItemResponse itemResponse;

    @Test
    void StockElementGetSet() {
        StockElement expected = new StockElement();
        expected.setId(1);
        expected.setItem(item);
        expected.setQuantity(10);

        StockElement actual = new StockElement();
        actual.setId(1);
        actual.setItem(item);
        actual.setQuantity(10);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getItem(), actual.getItem());
        assertEquals(expected.getQuantity(), actual.getQuantity());

    }
    @Test
    void StockRequestGetSet() {
        StockRequest expected = new StockRequest();
        expected.setItemId(1);
        expected.setQuantity(10);

        StockRequest actual = new StockRequest();
        actual.setItemId(1);
        actual.setQuantity(10);

        assertEquals(expected.getItemId(), actual.getItemId());
        assertEquals(expected.getQuantity(),actual.getQuantity());
    }

    @Test
    void StockResponseGetSet() {
        StockResponse expected = new StockResponse();
        expected.setId(1);
        expected.setItem(itemResponse);
        expected.setQuantity(10);

        StockResponse actual = new StockResponse();
        actual.setId(1);
        actual.setItem(itemResponse);
        actual.setQuantity(10);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getItem(),actual.getItem());
        assertEquals(expected.getQuantity(),actual.getQuantity());
    }

    @Test
    void StockSearchGetSet() {
        StockSearch expected = new StockSearch();
        expected.setQuantity(10);

        StockSearch actual = new StockSearch();
        actual.setQuantity(10);

        assertEquals(expected.getQuantity(), actual.getQuantity());

    }
}
