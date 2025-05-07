package com.fyuizee.gamingapi.persistence.mapper;

import com.fyuizee.gamingapi.controller.gamergamelink.dto.response.GamerGameLinkResponse;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.GamerProgress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GamerProgressMapper {

    @Mapping(source = "id.gamerId", target = "gamerId")
    @Mapping(source = "id.gameId", target = "gameId")
    @Mapping(source = "level", target = "levelType")
    GamerGameLinkResponse toResponse(GamerProgress gamerProgress);

}
