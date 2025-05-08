package com.fyuizee.gamingapi.persistence.repository.geography;

import com.fyuizee.gamingapi.persistence.domain.geography.GeographyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GeographyRepository extends JpaRepository<GeographyEntity, Integer> {

    @Query("FROM GeographyEntity g WHERE g.name ilike :name")
    Optional<GeographyEntity> findByName(String name);

}
