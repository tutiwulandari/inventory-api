package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repositories.UnitRepository;
import com.enigma.api.inventory.repositories.impl.UnitServiceImpl;
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
class UnitServiceTest {

    @InjectMocks
    private UnitServiceImpl service;

    @Mock
    private UnitRepository repository;

    @Test
    void shouldSave() {
        Unit input = new Unit();
        input.setCode("xyz");
        input.setDescription("XYZ");

        Unit output = new Unit();
        output.setId(1);
        output.setCode("xyz");
        output.setDescription("XYZ");

        when(repository.save(any())).thenReturn(output);

        Unit result = service.save(input);

        assertEquals(output, result);
    }
    @Test
    void shouldRemove() {
        Unit output = new Unit();
        output.setId(1);

        when(repository.findById(anyInt())).thenReturn(Optional.of(output));

        Unit result = service.removeById(output.getId());
        assertEquals(output,result);
    }

    @Test
    void shouldReturnFindUnitById() {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("xyz");
        unit.setDescription("XYZ");

        given(repository.findById(1)).willReturn(Optional.of(unit));

        Optional<Unit> expected = Optional.ofNullable(service.findById(1));
        assertThat(expected).isNotNull();
    }

}
