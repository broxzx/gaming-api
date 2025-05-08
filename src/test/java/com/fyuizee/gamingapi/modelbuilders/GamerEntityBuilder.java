package com.fyuizee.gamingapi.modelbuilders;

import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import com.fyuizee.gamingapi.persistence.domain.geography.GeographyEntity;

import java.time.Instant;
import java.util.UUID;

public class GamerEntityBuilder {

    public static GamerEntity buildBasicGamerEntity(UUID id, String username, String email, GeographyEntity geographyEntity) {
        return GamerEntity.builder()
                .id(id)
                .username(username)
                .email(email)
                .geographyEntity(geographyEntity)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

}
