package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.repositories.StockRepository;
import com.enigma.api.inventory.repositories.impl.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {
    @InjectMocks
    private StockServiceImpl service;

    @Mock
    private StockRepository repository;

    @Test
    void shouldSave() {
        Stock input = new Stock();
        input.setQuantity(10);
        input.setItem(input.getItem());

        Stock output = new Stock();
        output.setId(1);
        output.setQuantity(10);
        output.setItem(output.getItem());

        when(repository.save(any())).thenReturn(output);

        Stock result = service.save(input);

        assertEquals(output, result);
    }
    @Test
    void shouldRemove() {
        Stock output = new Stock();
        output.setId(1);

        when(repository.findById(anyInt())).thenReturn(Optional.of(output));

        Stock result = service.removeById(output.getId());
        assertEquals(output,result);
    }
    @Test
    void shouldReturnFindStockById() {
        Stock stock = new Stock();
        stock.setId(1);
        stock.setQuantity(10);
        stock.setItem(stock.getItem());

        given(repository.findById(1)).willReturn(Optional.of(stock));

        Optional<Stock> expected = Optional.ofNullable(service.findById(1));
        assertThat(expected).isNotNull();
    }

}
