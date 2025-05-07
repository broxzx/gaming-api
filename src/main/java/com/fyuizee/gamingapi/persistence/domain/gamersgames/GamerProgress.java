package com.fyuizee.gamingapi.persistence.domain.gamersgames;

import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.id.GamersGamesId;
import com.fyuizee.gamingapi.persistence.domain.games.GameEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Table(name = "gamers_games")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GamerProgress {

    @EmbeddedId
    private GamersGamesId id;

    @Column(name = "level", nullable = false)
    @Enumerated(EnumType.STRING)
    private LevelType level;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private GameEntity gameEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("gamerId")
    @JoinColumn(name = "gamer_id")
    private GamerEntity gamerEntity;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

}
