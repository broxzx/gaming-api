package com.fyuizee.gamingapi.service.gamer;

import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerByLevelResponse;
import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerResponse;
import com.fyuizee.gamingapi.exceptions.DataNotFoundException;
import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import com.fyuizee.gamingapi.persistence.mapper.GamerMapper;
import com.fyuizee.gamingapi.persistence.repository.gamer.GamerRepository;
import com.fyuizee.gamingapi.persistence.repository.gamer.models.GamerSearchResult;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GamerService {

    private final GamerRepository repository;
    private final GamerMapper mapper;

    public GamerEntity getGamerById(@NotNull UUID gamerId) {
        return repository.findById(gamerId)
                .orElseThrow(() -> new DataNotFoundException(gamerId, GamerEntity.class));
    }

    public Slice<GamerResponse> searchForGamer(LevelType levelType, String gameName, String geography, Pageable pageable) {
        String level = Optional.ofNullable(levelType).map(Enum::name).orElse(null);
        return repository.searchForUser(level, gameName, geography, pageable, GamerSearchResult.class)
                .map(mapper::toResponse);
    }

    public GamerByLevelResponse getGamersByLevelResponse(LevelType levelType) {
        Map<String, List<GamerResponse>> data = repository.findByType(levelType.name(), GamerSearchResult.class)
                .stream()
                .collect(Collectors.groupingBy(
                        GamerSearchResult::getGame, Collectors.mapping(mapper::toResponse, Collectors.toList())
                ));

        return new GamerByLevelResponse(data);
    }

}