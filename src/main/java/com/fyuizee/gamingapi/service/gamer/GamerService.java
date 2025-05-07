package com.fyuizee.gamingapi.service.gamer;

import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerResponse;
import com.fyuizee.gamingapi.exceptions.DataNotFoundException;
import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import com.fyuizee.gamingapi.persistence.mapper.GamerMapper;
import com.fyuizee.gamingapi.persistence.repository.gamer.GamerRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GamerService {

    private final GamerRepository repository;
    private final GamerMapper mapper;

    public GamerEntity getGamerById(@NotNull UUID gamerId) {
        return repository.findById(gamerId)
                .orElseThrow(() -> new DataNotFoundException(gamerId, GamerEntity.class));
    }

    public Slice<GamerResponse> searchForGamer(LevelType levelType, String gameName, String geography) {
        String level = Optional.ofNullable(levelType).map(Enum::name).orElse(null);
        return repository.searchForUser(level, gameName, geography, GamerResponse.class);
    }
}
