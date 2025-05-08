package com.fyuizee.gamingapi.controller.gamer.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GamerResponse {

    private String username;
    private String game;
    private String email;
    private String geography;
    private Instant createdAt;
    private Instant updatedAt;


}
