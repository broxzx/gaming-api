package com.fyuizee.gamingapi;

import com.fyuizee.gamingapi.controller.gamer.dto.response.GamerResponse;
import com.fyuizee.gamingapi.service.gamer.GamerService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Slice;

@SpringBootApplication
public class GamingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamingApiApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(GamerService gamerService) {
        return args -> {
            Slice<GamerResponse> gamerResponses = gamerService.searchForGamer(null, null, null);

            System.out.println(gamerResponses);
        };
    }

}
