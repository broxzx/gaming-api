package com.fyuizee.gamingapi.controller.gamer;

import com.fyuizee.gamingapi.controller.gamer.dto.request.CreateGamerRequest;
import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerByLevelResponse;
import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerResponse;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import com.fyuizee.gamingapi.service.gamer.GamerService;
import com.fyuizee.gamingapi.utils.CachedSlice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gamers")
@RequiredArgsConstructor
public class GamerController {

    private final GamerService gamerService;

    @PostMapping
    public ResponseEntity<GamerResponse> saveGamer(@RequestBody @Valid CreateGamerRequest createGamerRequest) {
        return ResponseEntity.ok(gamerService.saveGamer(createGamerRequest));
    }

    @GetMapping
    public ResponseEntity<CachedSlice<GamerResponse>> searchForGamer(
            @RequestParam(value = "level", required = false) LevelType levelType,
            @RequestParam(value = "game", required = false) String gameName,
            @RequestParam(value = "geography", required = false) String geography,
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(gamerService.searchForGamer(levelType, gameName, geography, pageable));
    }

    @GetMapping("/levels/{level}")
    public ResponseEntity<GamerByLevelResponse> getGamersByLevelResponse(@PathVariable("level") LevelType levelType) {
        return ResponseEntity.ok(gamerService.getGamersByLevelResponse(levelType));
    }

}
