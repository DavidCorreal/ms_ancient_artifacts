package co.com.bancolombia.source.manuscript;

import co.com.bancolombia.source.manuscript.dto.ManuscriptDTO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ManuscriptRepository extends ReactiveCrudRepository<ManuscriptDTO, Long> { }