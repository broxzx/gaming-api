package com.fyuizee.gamingapi.service.geography;

import com.fyuizee.gamingapi.exceptions.DataNotFoundException;
import com.fyuizee.gamingapi.modelbuilders.GeographyBuilder;
import com.fyuizee.gamingapi.persistence.domain.geography.GeographyEntity;
import com.fyuizee.gamingapi.persistence.domain.geography.enums.GeographyName;
import com.fyuizee.gamingapi.persistence.repository.geography.GeographyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeographyServiceTest {

    @InjectMocks
    private GeographyService service;

    @Mock
    private GeographyRepository repository;

    @Nested
    class GetByName {

        private Integer id;
        private String geography;
        private GeographyEntity geographyEntity;

        @BeforeEach
        void setUp() {
            this.id = 1;
            this.geography = GeographyName.EUROPE.getValue();
            this.geographyEntity = GeographyBuilder.buildBasicGeographyEntity(id, geography);
        }

        @Test
        void givenExistingGeography_whenGetByName_thenReturnEntity() {
            when(repository.findByName(geography)).thenReturn(Optional.of(geographyEntity));

            GeographyEntity actual = service.getByName(geography);

            assertEquals(actual.getName(), geography);
            assertEquals(actual.getId(), id);
        }

        @Test
        void givenNonExistentGeography_whenGetByName_thenException() {
            when(repository.findByName(geography)).thenReturn(Optional.empty());

            assertThrows(DataNotFoundException.class, () -> service.getByName(this.geography));
        }

    }

}
