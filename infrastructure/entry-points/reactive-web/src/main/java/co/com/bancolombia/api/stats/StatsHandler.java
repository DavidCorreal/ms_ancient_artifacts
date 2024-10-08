package co.com.bancolombia.api.stats;

import co.com.bancolombia.api.stats.dto.StatsResponseDTO;
import co.com.bancolombia.usecase.stats.StatsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StatsHandler {

    private final StatsUseCase statsUseCase;

    public Mono<ServerResponse> getStats(ServerRequest serverRequest) {
        return statsUseCase.consultStatistics()
                .flatMap(containsArtifactClue ->
                        ServerResponse
                                .status(HttpStatus.OK)
                                .bodyValue(StatsResponseDTO.builder()
                                        .countClueFound(containsArtifactClue.getCountClueFound())
                                        .countClueNoFound(containsArtifactClue.getCountNoClue())
                                        .ratio(containsArtifactClue.getRatio()).build())
                );
    }

}
