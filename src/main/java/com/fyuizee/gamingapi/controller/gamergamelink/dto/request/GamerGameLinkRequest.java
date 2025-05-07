package com.fyuizee.gamingapi.controller.gamergamelink.dto.request;

import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GamerGameLinkRequest {

    @NotNull
    private UUID gamerId;
    @NotNull
    private UUID gameId;
    @NotNull
    private LevelType levelType;

}
