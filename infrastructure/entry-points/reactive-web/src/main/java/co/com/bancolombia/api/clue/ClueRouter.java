package co.com.bancolombia.api.clue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ClueRouter {

    @Bean
    public RouterFunction<ServerResponse> clueRoutes(ClueHandler handler) {
        return route(POST("/clue/"), handler::analyseClue);
    }

}