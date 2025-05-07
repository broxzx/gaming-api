package com.fyuizee.gamingapi.controller.gamergamelink;

import com.fyuizee.gamingapi.controller.gamergamelink.dto.request.GamerGameLinkRequest;
import com.fyuizee.gamingapi.controller.gamergamelink.dto.response.GamerGameLinkResponse;
import com.fyuizee.gamingapi.service.gamergamelink.GamerProgressLinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gamers/link/games")
@RequiredArgsConstructor
public class GamerProgressLinkController {

    private final GamerProgressLinkService gamerProgressLinkService;

    @PostMapping
    public ResponseEntity<GamerGameLinkResponse> linkGamerToGame(@RequestBody @Valid GamerGameLinkRequest gamerGameLinkRequest) {
        return ResponseEntity.ok(gamerProgressLinkService.linkGamerToGame(gamerGameLinkRequest));
    }

}
