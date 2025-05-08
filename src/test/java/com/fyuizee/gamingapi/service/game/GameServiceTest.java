package com.fyuizee.gamingapi.service.game;

import com.fyuizee.gamingapi.controller.game.dto.request.CreateGameRequest;
import com.fyuizee.gamingapi.controller.game.dto.response.GameResponse;
import com.fyuizee.gamingapi.exceptions.DataNotFoundException;
import com.fyuizee.gamingapi.exceptions.EntityAlreadyExistsException;
import com.fyuizee.gamingapi.modelbuilders.GameEntityBuilder;
import com.fyuizee.gamingapi.persistence.domain.games.GameEntity;
import com.fyuizee.gamingapi.persistence.mapper.GameMapper;
import com.fyuizee.gamingapi.persistence.repository.game.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @InjectMocks
    private GameService service;

    @Mock
    private GameRepository repository;

    @Mock
    private GameMapper mapper;


    @Nested
    class GetGameById {

        private UUID gameId;
        private GameEntity gameEntity;

        @BeforeEach
        void setUp() {
            this.gameId = UUID.randomUUID();
            this.gameEntity = GameEntityBuilder.buildBasicGameEntity(gameId);
        }

        @Test
        void givenExistingGameId_whenGettingGameById_thenReturnGame() {
            when(repository.findById(this.gameId)).thenReturn(Optional.of(gameEntity));

            GameEntity actual = service.getGameById(gameId);

            assertEquals(actual.getId(), gameEntity.getId());
            assertEquals(actual.getName(), gameEntity.getName());
            assertEquals(actual.getReleased(), gameEntity.getReleased());
        }

        @Test
        void givenNonExistentGameId_whenGettingGameById_thenThrowException() {
            when(repository.findById(this.gameId)).thenReturn(Optional.empty());

            assertThrows(DataNotFoundException.class, () -> service.getGameById(this.gameId));
        }
    }

    @Nested
    class CreateGame {

        private UUID gameId;
        private GameEntity gameEntity;
        private CreateGameRequest createGamerRequest;
        private GameResponse gameResponse;

        @BeforeEach
        void setUp() {
            this.gameId = UUID.randomUUID();
            this.gameEntity = GameEntityBuilder.buildBasicGameEntity(gameId);
            this.createGamerRequest = CreateGameRequest.builder()
                    .name("mocked username")
                    .released(LocalDate.now())
                    .build();

            this.gameResponse = GameResponse.builder()
                    .name(createGamerRequest.getName())
                    .released(createGamerRequest.getReleased())
                    .build();
        }

        @Test
        void givenNonExistentCreateGameRequest_whenCreateGame_thenCreateGame() {
            GameEntity mappedGame = GameEntity.builder()
                    .name(this.createGamerRequest.getName())
                    .released(this.createGamerRequest.getReleased())
                    .build();

            GameEntity savedGame = GameEntity.builder()
                    .id(gameId)
                    .name(this.createGamerRequest.getName())
                    .released(this.createGamerRequest.getReleased())
                    .build();


            when(repository.findByName(this.createGamerRequest.getName())).thenReturn(Optional.empty());
            when(mapper.toEntity(createGamerRequest)).thenReturn(mappedGame);
            when(repository.save(mappedGame)).thenReturn(savedGame);
            when(mapper.toResponse(savedGame)).thenReturn(gameResponse);

            GameResponse actual = service.createGame(this.createGamerRequest);

            assertEquals(actual.getName(), createGamerRequest.getName());
            assertEquals(actual.getReleased(), createGamerRequest.getReleased());
        }


        @Test
        void givenExistingCreateGameRequest_whenCreateGame_thenCreateGame() {
            when(repository.findByName(this.createGamerRequest.getName())).thenReturn(Optional.of(gameEntity));

            assertThrows(EntityAlreadyExistsException.class, () -> service.createGame(createGamerRequest));
        }
    }

}
