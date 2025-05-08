package com.fyuizee.gamingapi.persistence.repository.game;

import com.fyuizee.gamingapi.persistence.domain.games.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<GameEntity, UUID> {
}
