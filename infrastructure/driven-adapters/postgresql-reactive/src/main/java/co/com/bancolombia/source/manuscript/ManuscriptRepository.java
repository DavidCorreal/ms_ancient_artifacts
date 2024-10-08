package co.com.bancolombia.source.manuscript;

import co.com.bancolombia.source.manuscript.dto.ManuscriptDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ManuscriptRepository {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public Mono<ManuscriptDTO> save(ManuscriptDTO manuscriptDTO) {
        return r2dbcEntityTemplate.insert(manuscriptDTO);
    }

}
