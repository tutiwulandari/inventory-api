package com.enigma.api.inventory.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PageSearchTest {

    private PageSearch pageSearch;

    @Test
    void setPageSearch() {
        PageSearch expected = new PageSearch();
        expected.setPage(1);
        expected.setSize(1);
        expected.setSort(Sort.Direction.ASC);

        PageSearch actual = new PageSearch();
        actual.setPage(1);
        actual.setSize(1);
        actual.setSort(Sort.Direction.ASC);

        assertEquals(expected.getPage(), actual.getPage());
        assertEquals(expected.getSize(), actual.getSize());
        assertEquals(expected.getSort(), actual.getSort());
    }

}
