package com.fyuizee.gamingapi.service.gamerprogress;

import com.fyuizee.gamingapi.exceptions.EntityAlreadyExistsException;
import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.GamerProgress;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.id.GamersGamesId;
import com.fyuizee.gamingapi.persistence.domain.games.GameEntity;
import com.fyuizee.gamingapi.persistence.repository.gamerprogress.GamerProgressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GamerProgressService {

    private final GamerProgressRepository repository;

    @Transactional
    public GamerProgress saveGamerProgress(GamerEntity gamer, GameEntity game, LevelType level) {
        checkIfGamerProgressExists(gamer.getId(), game.getId());

        GamerProgress gamerProgress = GamerProgress.builder()
                .id(new GamersGamesId(game.getId(), gamer.getId()))
                .gamerEntity(gamer)
                .gameEntity(game)
                .level(level)
                .build();
        repository.save(gamerProgress);

        log.debug("saved gamer progress for gamer \"{}\" and game \"{}\"", gamer.getId(), game.getId());
        return gamerProgress;
    }

    private void checkIfGamerProgressExists(UUID gamerId, UUID gameId) {
        repository.findById(new GamersGamesId(gameId, gamerId))
                .ifPresent(unused -> {
                    throw new EntityAlreadyExistsException(gamerId + " and " + gameId, GamerProgress.class);
                });
    }

}
