package com.fyuizee.gamingapi.controller.gamer;

import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerResponse;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import com.fyuizee.gamingapi.service.gamer.GamerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gamers")
@RequiredArgsConstructor
public class GamerController {

    private final GamerService gamerService;

    @GetMapping
    public ResponseEntity<Slice<GamerResponse>> searchForGamer(
            @RequestParam(value = "level", required = false) LevelType levelType,
            @RequestParam(value = "game", required = false) String gameName,
            @RequestParam(value = "geography", required = false) String geography
            ) {
        return ResponseEntity.ok(gamerService.searchForGamer(levelType, gameName, geography));
    }

}
