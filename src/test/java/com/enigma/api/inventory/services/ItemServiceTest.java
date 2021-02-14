package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.repositories.ItemRepository;
import com.enigma.api.inventory.repositories.impl.ItemServiceImpl;
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
class ItemServiceTest {

    @InjectMocks
    private ItemServiceImpl service;

    @Mock
    private ItemRepository repository;

    @Test
    void shouldSave() {
        Item input = new Item();
        input.setId(1);
        input.setName("aaa");
        input.setPrice(1000.0);


        Item output = new Item();
        output.setId(1);
        output.setName("aaa");
        output.setPrice(1000.0);

        when(repository.save(any())).thenReturn(output);

        Item result = service.save(input);

        assertEquals(output, result);
    }
    @Test
    void shouldRemove() {
        Item output = new Item();
        output.setId(1);

        when(repository.findById(anyInt())).thenReturn(Optional.of(output));

        Item result = service.removeById(output.getId());
        assertEquals(output,result);
    }

    @Test
    void shouldReturnFindItemById() {
        Item item = new Item();
        item.setId(1);
        item.setName("name");
        item.setPrice(1000.0);
        item.setUnit(item.getUnit());
        item.setStocks(item.getStocks());

        given(repository.findById(1)).willReturn(Optional.of(item));

        Optional<Item> expected = Optional.ofNullable(service.findById(1));
        assertThat(expected).isNotNull();
    }

//    @Test
//    void shouldReturnFindAll() {
//        List<Item> items = new ArrayList<>();
//        items.add(new Item(1,"name",1000.0));
//        items.add(new Item(2,"name",1000.0));
//        items.add(new Item(3,"name",1000.0));
//
//        given(repository.findAll()).willReturn(items);
//
//        List<Item> expected = service.findAll();
//        assertEquals(expected, items);
//    }


}
