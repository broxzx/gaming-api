package com.fyuizee.gamingapi.controller.gamergamelink.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GamerGameLinkResponse {

    private UUID gamerId;
    private UUID gameId;
    private LevelType levelType;

}
