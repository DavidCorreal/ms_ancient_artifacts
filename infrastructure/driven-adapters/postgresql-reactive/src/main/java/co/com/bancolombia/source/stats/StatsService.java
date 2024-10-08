package co.com.bancolombia.source.stats;

import co.com.bancolombia.model.stats.Stats;
import co.com.bancolombia.model.stats.gateway.StatsSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StatsService implements StatsSource {

    private final StatsRepository repository;

    public Mono<Stats> consultStatistics() {
        return repository.findFirstElement()
                .map(statsDTO -> new Stats(statsDTO.countClueFound(), statsDTO.countClueNoFound()));
    }

}