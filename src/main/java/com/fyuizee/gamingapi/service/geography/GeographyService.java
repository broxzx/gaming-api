package com.fyuizee.gamingapi.service.geography;

import com.fyuizee.gamingapi.exceptions.DataNotFoundException;
import com.fyuizee.gamingapi.persistence.domain.geography.GeographyEntity;
import com.fyuizee.gamingapi.persistence.repository.geography.GeographyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeographyService {

    private final GeographyRepository repository;

    public GeographyEntity getByName(String geography) {
        return repository.findByName(geography)
                .orElseThrow(() -> new DataNotFoundException(geography, GeographyEntity.class));
    }

}
