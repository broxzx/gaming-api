package com.fyuizee.gamingapi.service.game;

import com.fyuizee.gamingapi.controller.game.dto.request.CreateGameRequest;
import com.fyuizee.gamingapi.controller.game.dto.response.GameResponse;
import com.fyuizee.gamingapi.exceptions.DataNotFoundException;
import com.fyuizee.gamingapi.exceptions.EntityAlreadyExistsException;
import com.fyuizee.gamingapi.persistence.domain.games.GameEntity;
import com.fyuizee.gamingapi.persistence.mapper.GameMapper;
import com.fyuizee.gamingapi.persistence.repository.game.GameRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository repository;
    private final GameMapper mapper;

    public GameEntity getGameById(@NotNull UUID gameId) {
        log.debug("look up for game with id: {}", gameId);
        return repository.findById(gameId)
                .orElseThrow(() -> {
                    log.error("game with id \"{}\" not found", gameId);
                    return new DataNotFoundException(gameId, GameEntity.class);
                });
    }

    public GameResponse createGame(CreateGameRequest createGameRequest) {
        log.debug("creating game with by requests: {}", createGameRequest);
        checkIfGameExists(createGameRequest.getName());

        GameEntity mappedGame = mapper.toEntity(createGameRequest);
        GameEntity savedGameEntity = repository.save(mappedGame);

        log.debug("saved game by request {} and generated uuid: {}", createGameRequest, savedGameEntity.getId());
        return mapper.toResponse(savedGameEntity);
    }

    private void checkIfGameExists(String name) {
        log.debug("checking if game with name \"{}\" exists", name);
        repository.findByName(name)
                .ifPresent((unused) -> {
                    log.error("game with name \"{}\" already exists", name);
                    throw new EntityAlreadyExistsException(name, GameEntity.class);
                });
    }
}
