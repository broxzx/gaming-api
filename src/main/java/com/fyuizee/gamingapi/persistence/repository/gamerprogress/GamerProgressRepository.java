package com.fyuizee.gamingapi.persistence.repository.gamerprogress;

import com.fyuizee.gamingapi.persistence.domain.gamersgames.GamerProgress;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.id.GamersGamesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamerProgressRepository extends JpaRepository<GamerProgress, GamersGamesId> {
}
