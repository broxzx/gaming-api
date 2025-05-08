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
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;
    private final GameMapper mapper;

    public GameEntity getGameById(@NotNull UUID gameId) {
        return repository.findById(gameId)
                .orElseThrow(() -> new DataNotFoundException(gameId, GameEntity.class));
    }

    public GameResponse createGame(CreateGameRequest createGameRequest) {
        checkIfGameExists(createGameRequest.getName());

        GameEntity mappedGame = mapper.toEntity(createGameRequest);
        GameEntity savedGameEntity = repository.save(mappedGame);

        return mapper.toResponse(savedGameEntity);
    }

    private void checkIfGameExists(String name) {
        repository.findByName(name)
                .ifPresent((unused) -> {
                    throw new EntityAlreadyExistsException(name, GameEntity.class);
                });
    }
}
