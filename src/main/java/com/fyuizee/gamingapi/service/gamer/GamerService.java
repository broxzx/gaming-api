package com.fyuizee.gamingapi.service.gamer;

import com.fyuizee.gamingapi.controller.gamer.dto.request.CreateGamerRequest;
import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerByLevelResponse;
import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerResponse;
import com.fyuizee.gamingapi.exceptions.DataNotFoundException;
import com.fyuizee.gamingapi.exceptions.EntityAlreadyExistsException;
import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import com.fyuizee.gamingapi.persistence.domain.gamersgames.enums.LevelType;
import com.fyuizee.gamingapi.persistence.domain.geography.GeographyEntity;
import com.fyuizee.gamingapi.persistence.mapper.GamerMapper;
import com.fyuizee.gamingapi.persistence.repository.gamer.GamerRepository;
import com.fyuizee.gamingapi.persistence.repository.gamer.models.GamerSearchResult;
import com.fyuizee.gamingapi.service.geography.GeographyService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GamerService {

    private final GamerRepository repository;
    private final GamerMapper mapper;
    private final GeographyService geographyService;

    public GamerResponse saveGamer(CreateGamerRequest createGamerRequest) {
        log.debug("start saving gamer by request: {}", createGamerRequest);
        GeographyEntity geographyEntity = geographyService.getByName(createGamerRequest.getGeography());
        checkIfGamerExists(createGamerRequest.getEmail(), createGamerRequest.getUsername());

        GamerEntity gamerEntity = GamerEntity.builder()
                .username(createGamerRequest.getUsername())
                .email(createGamerRequest.getEmail())
                .geographyEntity(geographyEntity)
                .build();

        log.debug("saving gamer with email \"{}\" or username \"{}\"", createGamerRequest.getEmail(), createGamerRequest.getUsername());
        return mapper.toResponse(
                repository.save(gamerEntity)
        );
    }

    private void checkIfGamerExists(String email, String username) {
        log.debug("check if gamer with email \"{}\" or username \"{}\" exists", email, username);
        repository.findByEmailOrUsername(email, username)
                .ifPresent(gamerEntity -> {
                    log.error("gamer with email \"{}\" or username \"{}\" already exists", email, username);
                    throw new EntityAlreadyExistsException(List.of(email, username), GamerEntity.class);
                });

    }

    public GamerEntity getGamerById(@NotNull UUID gamerId) {
        return repository.findById(gamerId)
                .orElseThrow(() -> {
                    log.error("gamer by id {} not found", gamerId);
                    return new DataNotFoundException(gamerId, GamerEntity.class);
                });
    }

    public Slice<GamerResponse> searchForGamer(LevelType levelType, String gameName, String geography, Pageable pageable) {
        String level = Optional.ofNullable(levelType).map(Enum::name).orElse(null);
        log.debug("searching for gamer with parameters. level: {}, gameName: {}, geography: {}, pageable: {}", level, gameName, geography, pageable);
        return repository.searchForGamer(level, gameName, geography, pageable, GamerSearchResult.class)
                .map(mapper::toResponse);
    }

    public GamerByLevelResponse getGamersByLevelResponse(LevelType levelType) {
        log.debug("getting gamers by level: {}", levelType);
        Map<String, List<GamerResponse>> data = repository.findByType(levelType.name(), GamerSearchResult.class)
                .stream()
                .collect(Collectors.groupingBy(
                        GamerSearchResult::getGame, Collectors.mapping(mapper::toResponse, Collectors.toList())
                ));

        log.debug("obtained gamers by level: {}. data: {}", levelType, data);
        return new GamerByLevelResponse(data);
    }

}