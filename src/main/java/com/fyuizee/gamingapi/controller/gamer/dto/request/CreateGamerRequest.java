package com.fyuizee.gamingapi.controller.gamer.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateGamerRequest {

    @Size(min = 4, max = 64)
    private String username;

    @Email
    private String email;

    @NotBlank
    private String geography;

}
