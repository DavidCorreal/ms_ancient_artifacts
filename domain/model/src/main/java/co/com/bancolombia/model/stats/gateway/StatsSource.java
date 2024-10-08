package co.com.bancolombia.model.stats.gateway;

import co.com.bancolombia.model.stats.Stats;
import reactor.core.publisher.Mono;

public interface StatsSource {

    Mono<Stats> consultStatistics();

}