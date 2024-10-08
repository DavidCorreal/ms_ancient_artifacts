package co.com.bancolombia.api.clue;

import co.com.bancolombia.api.clue.dto.ClueRequestDTO;
import co.com.bancolombia.api.clue.dto.ClueResponseDTO;
import co.com.bancolombia.usecase.manuscript.ManusScriptsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClueHandler {

    private final ManusScriptsUseCase manusScriptsUseCase;

    public Mono<ServerResponse> analyseClue(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ClueRequestDTO.class)
                .flatMap(request -> manusScriptsUseCase.analyzeManuscript(request.manuscript()))
                .flatMap(containsArtifactClue ->
                        ServerResponse
                                .status(getStatus(containsArtifactClue))
                                .bodyValue(buildBodyResponse(containsArtifactClue))
                );
    }

    private ClueResponseDTO buildBodyResponse(Boolean containsArtifactClue) {
        String message;
        if(containsArtifactClue) message = "¡Congratulations, a clue has been found!";
        else message =  "Try again";
        return ClueResponseDTO.builder()
                .message(message)
                .build();
    }

    private HttpStatus getStatus(Boolean containsArtifactClue) {
        if(containsArtifactClue) return HttpStatus.OK;
        else return HttpStatus.FORBIDDEN;
    }

}