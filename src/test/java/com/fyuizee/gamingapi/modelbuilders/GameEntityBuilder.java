package com.fyuizee.gamingapi.modelbuilders;

import com.fyuizee.gamingapi.persistence.domain.games.GameEntity;

import java.time.LocalDate;
import java.util.UUID;

public class GameEntityBuilder {

    public static GameEntity buildBasicGameEntity(UUID id) {
        return GameEntity.builder()
                .id(id)
                .name("mocked name")
                .released(LocalDate.now())
                .build();
    }

}
