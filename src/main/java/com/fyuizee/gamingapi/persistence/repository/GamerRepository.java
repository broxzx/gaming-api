package com.fyuizee.gamingapi.persistence.repository;

import com.fyuizee.gamingapi.persistence.domain.gamers.GamerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GamerRepository extends JpaRepository<GamerEntity, UUID> {
}
