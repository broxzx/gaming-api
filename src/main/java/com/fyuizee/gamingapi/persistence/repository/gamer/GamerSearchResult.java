package com.fyuizee.gamingapi.persistence.repository.gamer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GamerSearchResult {

    private UUID id;
    private String username;
    private String email;
    private String geography;
    private Instant createdAt;
    private Instant updatedAt;

}
