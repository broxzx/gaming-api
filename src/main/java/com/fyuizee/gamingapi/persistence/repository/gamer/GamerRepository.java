package com.fyuizee.gamingapi.persistence.repository.gamer;

import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface GamerRepository extends JpaRepository<GamerEntity, UUID> {

    @Query(value = "SELECT gamer.*, geo.name AS geography FROM gamers gamer " +
                   "JOIN geography geo ON (geo.id = gamer.geography_id)" +
                   "LEFT JOIN gamers_games gg ON (gg.gamer_id = gamer.id)" +
                   "JOIN games game ON (gg.game_id = game.id)" +
                   "WHERE gg.level = COALESCE(CAST(:level AS level_type), gg.level)" +
                   "AND game.name = COALESCE(:game, game.name)" +
                   "AND geo.name = COALESCE(:geography, geo.name)" +
                   "ORDER BY gamer.created_at", nativeQuery = true)
    <T> Slice<T> searchForUser(@Param("level") String level, @Param("game") String game, @Param("geography") String geography, Class<T> clazz);


}
