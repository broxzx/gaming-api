package com.fyuizee.gamingapi.service.gamergamelink;

import com.fyuizee.gamingapi.controller.gamergamelink.dto.request.GamerGameLinkRequest;
import com.fyuizee.gamingapi.controller.gamergamelink.dto.response.GamerGameLinkResponse;
import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import com.fyuizee.gamingapi.persistence.domain.games.GameEntity;
import com.fyuizee.gamingapi.persistence.mapper.GamerProgressMapper;
import com.fyuizee.gamingapi.service.game.GameService;
import com.fyuizee.gamingapi.service.gamer.GamerService;
import com.fyuizee.gamingapi.service.gamerprogress.GamerProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GamerGameLinkService {

    private final GameService gameService;
    private final GamerService gamerService;
    private final GamerProgressService gamerProgressService;
    private final GamerProgressMapper gamerProgressMapper;

    public GamerGameLinkResponse linkGamerToGame(GamerGameLinkRequest request) {
        GameEntity game = gameService.getGameById(request.getGameId());
        GamerEntity gamer = gamerService.getGamerById(request.getGamerId());

        return gamerProgressMapper.toResponse(
                gamerProgressService.saveGamerProgress(gamer, game, request.getLevelType())
        );
    }
}
