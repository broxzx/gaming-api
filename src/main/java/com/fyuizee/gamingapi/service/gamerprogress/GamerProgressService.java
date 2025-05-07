package com.fyuizee.gamingapi.service.gamerprogress;

import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.GamerProgress;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import com.fyuizee.gamingapi.persistence.domain.games.GameEntity;
import com.fyuizee.gamingapi.persistence.repository.GamerProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GamerProgressService {

    private final GamerProgressRepository repository;

    @Transactional
    public GamerProgress saveGamerProgress(GamerEntity gamer, GameEntity game, LevelType level) {
        GamerProgress gamerProgress = GamerProgress.builder()
                .gamerEntity(gamer)
                .gameEntity(game)
                .level(level)
                .build();
        repository.save(gamerProgress);

        return gamerProgress;
    }

}
