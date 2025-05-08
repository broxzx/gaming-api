package com.fyuizee.gamingapi.persistence.domain.gamersgames.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GamersGamesId {

    @Column(name = "game_id", nullable = false)
    private UUID gameId;

    @Column(name = "gamer_id", nullable = false)
    private UUID gamerId;

}
