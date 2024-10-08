package co.com.bancolombia.usecase.manuscript;

import co.com.bancolombia.model.manuscritp.Manuscript;
import co.com.bancolombia.model.manuscritp.gateway.ManuscriptSource;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ManusScriptsUseCase {

    private final ManuscriptSource source;

    public Mono<Boolean> analyzeManuscript(String[] manuscript) {
        return Mono.just(AnalyzeOldManuscript.containsArtifactClue(manuscript))
                .map(result -> {
                        source.save(Manuscript.builder().manuscript(manuscript).clueFound(result).build()).subscribe();
                        return result;
                });
    }

}