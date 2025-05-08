package com.fyuizee.gamingapi.controller.gamer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GamerByLevelResponse {

    private Map<String, List<GamerResponse>> data;

}
