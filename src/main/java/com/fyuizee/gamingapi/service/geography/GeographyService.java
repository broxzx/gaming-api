package com.fyuizee.gamingapi.service.geography;

import com.fyuizee.gamingapi.exceptions.DataNotFoundException;
import com.fyuizee.gamingapi.persistence.domain.geography.GeographyEntity;
import com.fyuizee.gamingapi.persistence.repository.geography.GeographyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeographyService {

    private final GeographyRepository repository;

    public GeographyEntity getByName(String geography) {
        return repository.findByName(geography)
                .orElseThrow(() -> {
                    log.error("geography with name \"{}\" not found", geography);
                    return new DataNotFoundException(geography, GeographyEntity.class);
                });
    }

}
