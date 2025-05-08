package com.fyuizee.gamingapi.persistence.repository.geography;

import com.fyuizee.gamingapi.persistence.domain.geography.GeographyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeographyRepository extends JpaRepository<GeographyEntity, Integer> {
}
