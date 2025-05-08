package com.fyuizee.gamingapi.persistence.mapper;

import com.fyuizee.gamingapi.controller.game.dto.request.CreateGameRequest;
import com.fyuizee.gamingapi.controller.game.dto.response.GameResponse;
import com.fyuizee.gamingapi.persistence.domain.games.GameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {

    GameEntity toEntity(CreateGameRequest createGameRequest);

    GameResponse toResponse(GameEntity gameEntity);

}
