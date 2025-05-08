package com.fyuizee.gamingapi.persistence.repository.gamer;

import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import jakarta.validation.constraints.Email;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GamerRepository extends JpaRepository<GamerEntity, UUID> {

    @Query(value = "SELECT gamer.id, gamer.username, game.name as game, gamer.email, gamer.created_at, gamer.updated_at, geo.name AS geography FROM gamers gamer " +
                   "JOIN geography geo ON (geo.id = gamer.geography_id)" +
                   "LEFT JOIN gamers_games gg ON (gg.gamer_id = gamer.id)" +
                   "JOIN games game ON (gg.game_id = game.id)" +
                   "WHERE gg.level like COALESCE(:level, gg.level)" +
                   "AND game.name ilike COALESCE(:game, game.name)" +
                   "AND geo.name ilike COALESCE(:geography, geo.name)" +
                   "ORDER BY gamer.created_at", nativeQuery = true)
    <T> Slice<T> searchForGamer(@Param("level") String level, @Param("game") String game, @Param("geography") String geography,
                                Pageable pageable, Class<T> clazz);


    @Query(value = "SELECT gamer.id, gamer.username, game.name as game, gamer.email, gamer.created_at, gamer.updated_at, geo.name AS geography FROM gamers gamer " +
                   "JOIN geography geo ON (geo.id = gamer.geography_id)" +
                   "JOIN gamers_games gg ON (gg.gamer_id = gamer.id) " +
                   "JOIN games game ON (gg.game_id = game.id)" +
                   "WHERE gg.level like :level " +
                   "ORDER BY created_at", nativeQuery = true)
    <T> List<T> findByType(@Param("level") String levelType, Class<T> clazz);

    @Query("FROM GamerEntity g WHERE g.email ilike :email or g.username ilike :username")
    Optional<GamerEntity> findByEmailOrUsername(@Email String email, String username);

}
