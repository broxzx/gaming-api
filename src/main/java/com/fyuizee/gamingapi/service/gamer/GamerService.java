package com.fyuizee.gamingapi.service.gamer;

import com.fyuizee.gamingapi.exceptions.DataNotFoundException;
import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import com.fyuizee.gamingapi.persistence.repository.GamerRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GamerService {

    private final GamerRepository repository;

    public GamerEntity getGamerById(@NotNull UUID gamerId) {
        return repository.findById(gamerId)
                .orElseThrow(() -> new DataNotFoundException(gamerId, GamerEntity.class));
    }
}
