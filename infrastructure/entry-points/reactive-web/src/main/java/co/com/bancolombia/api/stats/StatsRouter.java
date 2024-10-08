package co.com.bancolombia.api.stats;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class StatsRouter {

    @Bean
    RouterFunction<ServerResponse> getStats(StatsHandler handler) {
        return route(GET("/stats"), handler::getStats);
    }

}