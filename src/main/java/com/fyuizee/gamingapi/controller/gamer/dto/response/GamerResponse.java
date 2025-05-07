package com.fyuizee.gamingapi.controller.gamer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GamerResponse {

    private String username;
    private String email;
    private String geography;
    private Instant createdAt;
    private Instant updatedAt;


}
