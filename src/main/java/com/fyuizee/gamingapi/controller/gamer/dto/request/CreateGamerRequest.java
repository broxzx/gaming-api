package com.fyuizee.gamingapi.controller.gamer.dto.request;

import com.fyuizee.gamingapi.persistence.domain.geography.enums.GeographyName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private GeographyName geography;

}
