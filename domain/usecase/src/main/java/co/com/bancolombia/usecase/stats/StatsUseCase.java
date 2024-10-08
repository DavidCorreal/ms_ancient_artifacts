package co.com.bancolombia.usecase.stats;

import co.com.bancolombia.model.stats.Stats;
import co.com.bancolombia.model.stats.gateway.StatsSource;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StatsUseCase {

    private final StatsSource source;

    public Mono<Stats> consultStatistics() {
        return source.consultStatistics();
    }

}