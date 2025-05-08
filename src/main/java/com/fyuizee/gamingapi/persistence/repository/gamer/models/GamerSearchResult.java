package com.fyuizee.gamingapi.persistence.repository.gamer.models;

import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Data
public class GamerSearchResult {

    private UUID id;
    private String username;
    private String game;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;
    private String geography;

    public GamerSearchResult(UUID id, String username, String game, String email, Timestamp createdAt, Timestamp updatedAt, String geography) {
        this.id = id;
        this.username = username;
        this.game = game;
        this.email = email;
        this.createdAt = createdAt != null ? createdAt.toInstant() : null;
        this.updatedAt = updatedAt != null ? updatedAt.toInstant() : null;
        this.geography = geography;
    }

}
