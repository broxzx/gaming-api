package com.fyuizee.gamingapi.controller.game;

import com.fyuizee.gamingapi.controller.game.dto.request.CreateGameRequest;
import com.fyuizee.gamingapi.controller.game.dto.response.GameResponse;
import com.fyuizee.gamingapi.service.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<GameResponse> createGame(@RequestBody CreateGameRequest createGameRequest) {
        return ResponseEntity.ok(gameService.createGame(createGameRequest));
    }

}
