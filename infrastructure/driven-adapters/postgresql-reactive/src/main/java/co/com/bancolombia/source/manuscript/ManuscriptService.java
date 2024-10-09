package co.com.bancolombia.source.manuscript;

import co.com.bancolombia.model.manuscritp.Manuscript;
import co.com.bancolombia.model.manuscritp.gateway.ManuscriptSource;
import co.com.bancolombia.source.manuscript.dto.ManuscriptDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ManuscriptService implements ManuscriptSource {

    private final ManuscriptRepository manuscriptRepository;

    @Override
    public Mono<Void> save(Manuscript manuscript) {
        var manuscriptEntity = ManuscriptDTO.builder()
                .manuscript(manuscript.manuscript())
                .clueFound(manuscript.clueFound())
                .build();

        return manuscriptRepository.save(manuscriptEntity).then();
    }

}