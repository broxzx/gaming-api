package com.fyuizee.gamingapi.modelbuilders;

import com.fyuizee.gamingapi.persistence.domain.geography.GeographyEntity;

public class GeographyBuilder {

    public static GeographyEntity buildBasicGeographyEntity(Integer id, String name) {
        return GeographyEntity.builder()
                .id(id)
                .name(name)
                .build();
    }

}
