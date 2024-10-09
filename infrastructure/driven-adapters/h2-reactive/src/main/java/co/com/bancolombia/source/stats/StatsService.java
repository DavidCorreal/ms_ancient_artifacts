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

    /*public Mono<Stats> consultStatistics() {
        return repository.findById(1L)
                .map(statsDTO -> new Stats(statsDTO.countClueFound(), statsDTO.countClueNoFound()));
    }*/

    public Mono<Stats> consultStatistics() {
        return repository.consultStats()
                .map(statsDTO -> new Stats(statsDTO.countClueFound(), statsDTO.countClueNoFound()));
    }

    /*public Mono<Void> updateStatistics(Boolean clueFound) {
        return Mono.just(clueFound)
                .flatMap(isTrue -> {
                    if (isTrue) {
                        return repository.incrementCountClueFound(1L).then();
                    } else {
                        return repository.incrementCountClueNoFound(1L).then();
                    }
                });
    }*/

}