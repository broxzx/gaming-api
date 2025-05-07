package com.fyuizee.gamingapi.persistence.mapper;

import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerResponse;
import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GamerMapper {

    @Mapping(source = "geographyEntity.name", target = "geography")
    GamerResponse toResponse(GamerEntity gamerEntity);

    GamerResponse toResponse(Object gamerEntity);

}
