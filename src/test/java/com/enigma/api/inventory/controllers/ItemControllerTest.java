package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.ItemRequest;
import com.enigma.api.inventory.models.ItemResponse;
import com.enigma.api.inventory.models.UnitModel;
import com.enigma.api.inventory.services.FileService;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.UnitService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SuppressWarnings("ALL")
@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnitService unitService;

    @MockBean
    private ItemService service;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private Unit unit;

    @MockBean
    private FileService fileService;

    @MockBean
    private ItemResponse itemResponse;

    @Test
    void addShouldSuccess() throws Exception {
        Item item = new Item();
        item.setId(1);
        item.setName("name");
        item.setPrice(10.0);
        item.setUnit(unit);

        Unit unit = new Unit();
        UnitModel unitModel = new UnitModel();

        ItemResponse response = new ItemResponse();
        response.setId(item.getId());
        response.setName(item.getName());
        response.setPrice(item.getPrice());
        response.setUnit(unitModel);

        when(modelMapper.map(any(ItemRequest.class), any(Class.class))).thenReturn(item);
        when(unitService.findById(anyInt())).thenReturn(unit);
        when(service.save(any(Item.class))).thenReturn(item);
        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(response);

        RequestBuilder request = post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"wulan\", \"price\": \"10.0\",\"unitId\": \"1\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(item.getName())))
                .andExpect(jsonPath("$.data.price", is(item.getPrice())));
    }

    @Test
    public void getShouldReturnEntity() throws Exception {

        Item item = new Item();
        item.setId(1);
        item.setName("Tepung");
        item.setPrice(5000.0);
        item.setUnit(unit);

        ItemResponse response = new ItemResponse();
        response.setId(item.getId());
        response.setName(item.getName());
        response.setPrice(item.getPrice());
        when(service.findById(1)).thenReturn(item);

        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(response);

        RequestBuilder request = get("/items/1")
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(item.getName())))
                .andExpect(jsonPath("$.data.price", is(item.getPrice())));
    }

    @Test
    void shouldDeleteEntity() throws Exception {

        Item item = new Item(1, "name", 1000.0);
        Mockito.when(service.removeById(1)).thenReturn(item);

        Integer modelId = 1;
        ItemResponse model = new ItemResponse();
        model.setId(modelId);
        model.setName("name");
        model.setPrice(1000.0);

        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(model);

        mvc.perform(delete("/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(item.getName())))
                .andExpect(jsonPath("$.data.price", is(item.getPrice())));

    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Page page = Page.empty();

        when(service.findAll(any(), anyInt(), anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/items");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }

    @Test
    void editShouldSuccess() throws Exception {
        Unit unit = new Unit();
        UnitModel unitModel = new UnitModel();

        Item item = new Item();
        item.setId(1);
        item.setName("ABC");
        item.setPrice(10.0);
        item.setUnit(item.getUnit());

        ItemResponse response = new ItemResponse();
        response.setId(item.getId());
        response.setName(item.getName());
        response.setPrice(item.getPrice());
        response.setUnit(unitModel);

        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(item);
        when(service.save(any(Item.class))).thenReturn(item);
        when(service.findById(anyInt())).thenReturn(item);
        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(response);


        RequestBuilder request = put("/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"wulan\", \"price\": \"10.0\",\"unitId\": \"1\"}");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(item.getName())));


    }


}

