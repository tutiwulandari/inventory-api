package com.enigma.api.inventory.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UnitTest {

    @Mock
    private List<Item> item;

    @Test
    void setUnitEntity() {
        LocalDateTime now = LocalDateTime.now();

        Unit expected = new Unit();
        expected.setId(1);
        expected.setCode("xyz");
        expected.setDescription("XYZ");
        expected.setItems(item);
        expected.setCreatedDate(now);
        expected.setModifiedDate(now);

        Unit actual = new Unit();
        actual.setId(1);
        actual.setCode("xyz");
        actual.setDescription("XYZ");
        actual.setItems(item);
        actual.setCreatedDate(now);
        actual.setModifiedDate(now);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getItems(), actual.getItems());
        assertEquals(expected.getCreatedDate(), actual.getCreatedDate());
        assertEquals(expected.getModifiedDate(), actual.getModifiedDate());

    }
}
