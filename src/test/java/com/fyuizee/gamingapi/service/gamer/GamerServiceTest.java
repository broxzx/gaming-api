package com.fyuizee.gamingapi.service.gamer;

import com.fyuizee.gamingapi.controller.gamer.dto.request.CreateGamerRequest;
import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerResponse;
import com.fyuizee.gamingapi.exceptions.DataNotFoundException;
import com.fyuizee.gamingapi.exceptions.EntityAlreadyExistsException;
import com.fyuizee.gamingapi.modelbuilders.GamerEntityBuilder;
import com.fyuizee.gamingapi.modelbuilders.GeographyBuilder;
import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import com.fyuizee.gamingapi.persistence.domain.geography.GeographyEntity;
import com.fyuizee.gamingapi.persistence.domain.geography.enums.GeographyName;
import com.fyuizee.gamingapi.persistence.mapper.GamerMapper;
import com.fyuizee.gamingapi.persistence.repository.gamer.GamerRepository;
import com.fyuizee.gamingapi.persistence.repository.gamer.models.GamerSearchResult;
import com.fyuizee.gamingapi.service.geography.GeographyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GamerServiceTest {

    @InjectMocks
    private GamerService service;

    @Mock
    private GeographyService geographyService;

    @Mock
    private GamerRepository repository;

    @Mock
    private GamerMapper mapper;


    @Nested
    class SaveGamer {

        private UUID id;
        private CreateGamerRequest createGamerRequest;
        private GeographyEntity geographyEntity;
        private GamerEntity persistedGamerEntity;
        private GamerResponse gamerResponse;
        private final Integer geographyId = 1;
        private final GeographyName geographyName = GeographyName.EUROPE;
        private final String geographyNameStr = GeographyName.EUROPE.getValue();

        @BeforeEach
        void setUp() {
            this.createGamerRequest = CreateGamerRequest.builder()
                    .username("mocker-username")
                    .email("mocked-gamer@gmail.com")
                    .geography(this.geographyName)
                    .build();
            this.geographyEntity = GeographyBuilder.buildBasicGeographyEntity(geographyId, geographyNameStr);
            this.persistedGamerEntity = GamerEntityBuilder.buildBasicGamerEntity(id, createGamerRequest.getUsername(), createGamerRequest.getEmail(), geographyEntity);
            this.gamerResponse = GamerResponse.builder()
                    .username(persistedGamerEntity.getUsername())
                    .game(persistedGamerEntity.getEmail())
                    .email(persistedGamerEntity.getEmail())
                    .geography(persistedGamerEntity.getGeographyEntity().getName())
                    .createdAt(persistedGamerEntity.getCreatedAt())
                    .updatedAt(persistedGamerEntity.getUpdatedAt())
                    .build();
        }

        @Test
        void givenNonExistentGamerRequest_whenSaveGamer_thenSaveGamer() {
            when(geographyService.getByName(this.createGamerRequest.getGeography().getValue())).thenReturn(this.geographyEntity);
            when(repository.findByEmailOrUsername(this.createGamerRequest.getEmail(), this.createGamerRequest.getUsername())).thenReturn(Optional.empty());
            when(repository.save(any(GamerEntity.class))).thenReturn(persistedGamerEntity);
            when(mapper.toResponse(persistedGamerEntity)).thenReturn(gamerResponse);

            GamerResponse actual = service.saveGamer(this.createGamerRequest);

            assertEquals(actual.getUsername(), gamerResponse.getUsername());
            assertEquals(actual.getEmail(), gamerResponse.getEmail());
            assertEquals(actual.getGame(), gamerResponse.getGame());
            assertEquals(actual.getGeography(), gamerResponse.getGeography());
            assertEquals(actual.getCreatedAt(), gamerResponse.getCreatedAt());
            assertEquals(actual.getUpdatedAt(), gamerResponse.getUpdatedAt());

            verify(geographyService).getByName(geographyName.getValue());
            verify(repository).findByEmailOrUsername(this.createGamerRequest.getEmail(), this.createGamerRequest.getUsername());
        }

        @Test
        void givenExistentGamerByUsernameRequest_whenSaveGamer_thenSaveGamer() {
            when(geographyService.getByName(this.createGamerRequest.getGeography().getValue())).thenReturn(this.geographyEntity);
            when(repository.findByEmailOrUsername(this.createGamerRequest.getEmail(), this.createGamerRequest.getUsername())).thenReturn(Optional.of(persistedGamerEntity));

            assertThrows(EntityAlreadyExistsException.class, () -> service.saveGamer(this.createGamerRequest));
        }
    }

    @Nested
    class GetGamerById {

        private UUID id;
        private GeographyEntity geographyEntity;
        private GamerEntity persistedGamerEntity;

        @BeforeEach
        void setUp() {
            this.id = UUID.randomUUID();
            this.geographyEntity = GeographyBuilder.buildBasicGeographyEntity(1, GeographyName.EUROPE.getValue());
            this.persistedGamerEntity = GamerEntityBuilder.buildBasicGamerEntity(id, "mocked username", "mocked-email@gmail.com", geographyEntity);
        }

        @Test
        void givenExistingUser_whenGetGamerById_thenReturnEntity() {
            when(repository.findById(eq(id))).thenReturn(Optional.ofNullable(persistedGamerEntity));

            GamerEntity actual = service.getGamerById(id);

            assertEquals(actual.getId(), persistedGamerEntity.getId());
            assertEquals(actual.getUsername(), persistedGamerEntity.getUsername());
            assertEquals(actual.getEmail(), persistedGamerEntity.getEmail());
            assertEquals(actual.getGeographyEntity(), persistedGamerEntity.getGeographyEntity());
            assertEquals(actual.getCreatedAt(), persistedGamerEntity.getCreatedAt());
            assertEquals(actual.getUpdatedAt(), persistedGamerEntity.getUpdatedAt());

            verify(repository).findById(id);
        }

        @Test
        void givenNonExistentUser_whenGetGamerById_thenException() {
            when(repository.findById(id)).thenReturn(Optional.empty());

            assertThrows(DataNotFoundException.class, () -> service.getGamerById(id));
        }
    }

    @Nested
    class SearchForGamerTests {

        private UUID id;
        private String gameName;
        private String geography;
        private LevelType levelType;
        private Pageable pageable;
        private GamerResponse gamerResponse;

        @BeforeEach
        void setUp() {
            Instant now = Instant.now();
            this.id = UUID.randomUUID();
            this.gameName = "The Witcher 3";
            this.geography = GeographyName.EUROPE.getValue();
            this.levelType = LevelType.PRO;
            this.pageable = PageRequest.of(0, 10);
            this.gamerResponse = GamerResponse.builder()
                    .username("mocked username")
                    .game(gameName)
                    .email("mocked-user@gmail.com")
                    .geography(geography)
                    .createdAt(now)
                    .updatedAt(now)
                    .build();
        }

        @Test
        void givenAllParameters_whenSearchForGamer_thenReturnSliceOfGamerResponses() {
            GamerSearchResult searchResult = new GamerSearchResult(id, "testUser", gameName, "test@email.com", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), this.geography);
            SliceImpl<GamerSearchResult> mockSlice = new SliceImpl<>(List.of(searchResult));

            when(repository.searchForGamer(eq(levelType.name()), eq(gameName), eq(geography), eq(pageable), eq(GamerSearchResult.class)))
                    .thenReturn(mockSlice);
            when(mapper.toResponse(searchResult)).thenReturn(gamerResponse);

            Slice<GamerResponse> result = service.searchForGamer(levelType, gameName, geography, pageable);

            assertThat(result).isNotNull();

            verify(repository).searchForGamer(levelType.name(), gameName, geography, pageable, GamerSearchResult.class);
            verify(mapper).toResponse(searchResult);
        }

        @Test
        void givenNullParameters_whenSearchForUser_thenReturnAllData() {
            when(repository.searchForGamer(isNull(), isNull(), isNull(), any(Pageable.class), eq(GamerSearchResult.class)))
                    .thenReturn(new SliceImpl<>(Collections.emptyList()));

            Slice<GamerResponse> result = service.searchForGamer(null, null, null, pageable);

            assertThat(result).isNotNull();
            assertThat(result.getContent().size()).isZero();
        }

        @Test
        void givenAndNoDataParameters_whenSearchForUser_thenReturnEmptySlice() {
            when(repository.searchForGamer(anyString(), anyString(), anyString(), any(Pageable.class), eq(GamerSearchResult.class)))
                    .thenReturn(new SliceImpl<>(Collections.emptyList()));

            Slice<GamerResponse> result = service.searchForGamer(levelType, gameName, geography, pageable);

            assertThat(result).isNotNull();
            assertThat(result.getContent().size()).isZero();
        }
    }

}
