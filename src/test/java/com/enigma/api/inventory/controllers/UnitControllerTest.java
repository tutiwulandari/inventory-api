package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.UnitModel;
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

@SuppressWarnings("ALL")
@WebMvcTest(UnitController.class)
class UnitControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnitService service;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    void addShouldSuccess() throws Exception {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("xyz");
        unit.setDescription("XYZ");

        when(service.save(any())).thenReturn(unit);

        UnitModel model = new UnitModel();
        model.setId(unit.getId());
        model.setCode(unit.getCode());
        model.setDescription(unit.getDescription());

        when(modelMapper.map(any(Unit.class), any(Class.class))).thenReturn(model);

        RequestBuilder request = post("/units")
                .contentType(MediaType.ALL.APPLICATION_JSON)
                .content(("{\"code\":\"xyz\",\"description\":\"XYZ\"}"));
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(unit.getCode())));
    }

    @Test
    public void getShouldReturnEntity() throws Exception {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("xyz");
        unit.setDescription("XYZ");

        when(service.findById(1)).thenReturn(unit);
        UnitModel model = new UnitModel();
        model.setId(1);
        model.setCode("xyz");
        model.setDescription("XYZ");
        when(modelMapper.map(any(Unit.class), any(Class.class))).thenReturn(model);

        mvc.perform(get("/units/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(unit.getCode())))
                .andExpect(jsonPath("$.data.description", is(unit.getDescription())));

    }

    @Test
    void shouldDeleteEntity()throws Exception {
        Unit unit = new Unit(1, "xyz", "XYZ");
        Mockito.when(service.removeById(1)).thenReturn(unit);

        Integer modelId = 1;
        UnitModel model = new UnitModel();
        model.setId(modelId);
        model.setCode("xyz");
        model.setDescription("XYZ");

        when(modelMapper.map(any(Unit.class), any(Class.class))).thenReturn(model);

        mvc.perform(delete("/units/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(unit.getCode())))
                .andExpect(jsonPath("$.data.description", is(unit.getDescription())));

    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Page page = Page.empty();

        when(service.findAll(any(), anyInt(),anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/units");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }

    @Test
    void editShouldSuccess() throws Exception {
        Unit unit = new Unit();
        unit.setId(1);
        when(service.findById(unit.getId())).thenReturn(unit);
        if (unit == null) {
            throw new EntityNotFoundException();
        }

        UnitModel model = new UnitModel();
        model.setId(unit.getId());
        when(modelMapper.map(any(Unit.class), any(Class.class))).thenReturn(model);

        mvc.perform(get("/units/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(unit.getCode())))
                .andExpect(jsonPath("$.data.description", is(unit.getDescription())));

        Unit entity = new Unit();
        entity.setId(unit.getId());
        when(service.save(any())).thenReturn(entity);

        RequestBuilder request = put("/units/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\" : \"xyz\", \"description\": \"XYZ\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(entity.getCode())));

    }
}
