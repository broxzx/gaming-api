package com.fyuizee.gamingapi.persistence.repository.gamer;

import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerResponse;
import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GamerRepository extends JpaRepository<GamerEntity, UUID> {

    @Query(value = "SELECT gamer.id, gamer.username, game.name as game, gamer.email, gamer.created_at, gamer.updated_at, geo.name AS geography FROM gamers gamer " +
                   "JOIN geography geo ON (geo.id = gamer.geography_id)" +
                   "LEFT JOIN gamers_games gg ON (gg.gamer_id = gamer.id)" +
                   "JOIN games game ON (gg.game_id = game.id)" +
                   "WHERE gg.level = COALESCE(CAST(:level AS level_type), gg.level)" +
                   "AND game.name = COALESCE(:game, game.name)" +
                   "AND geo.name = COALESCE(:geography, geo.name)" +
                   "ORDER BY gamer.created_at", nativeQuery = true)
    <T> Slice<T> searchForUser(@Param("level") String level, @Param("game") String game, @Param("geography") String geography,
                               Pageable pageable, Class<T> clazz);


    @Query(value = "SELECT gamer.id, gamer.username, game.name as game, gamer.email, gamer.created_at, gamer.updated_at, geo.name AS geography FROM gamers gamer " +
                   "JOIN geography geo ON (geo.id = gamer.geography_id)" +
                   "JOIN gamers_games gg ON (gg.gamer_id = gamer.id) " +
                   "JOIN games game ON (gg.game_id = game.id)" +
                   "WHERE gg.level = CAST(:level AS level_type)" +
                   "ORDER BY created_at", nativeQuery = true)
    <T> List<T> findByType(@Param("level") String levelType, Class<T> clazz);
}
