package com.fyuizee.gamingapi.persistence.repository;

import com.fyuizee.gamingapi.persistence.domain.gamersgames.GamerGameInfo;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.id.GamersGamesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamerGameRepository extends JpaRepository<GamerGameInfo, GamersGamesId> {
}
