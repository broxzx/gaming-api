package com.fyuizee.gamingapi.service.game;

import com.fyuizee.gamingapi.exceptions.DataNotFoundException;
import com.fyuizee.gamingapi.persistence.domain.games.GameEntity;
import com.fyuizee.gamingapi.persistence.repository.game.GameRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;

    public GameEntity getGameById(@NotNull UUID gameId) {
        return repository.findById(gameId)
                .orElseThrow(() -> new DataNotFoundException(gameId, GameEntity.class));
    }
}
