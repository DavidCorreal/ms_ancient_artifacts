package co.com.bancolombia.source.manuscript;

import co.com.bancolombia.source.manuscript.dto.ManuscriptDTO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManuscriptRepository extends R2dbcRepository<ManuscriptDTO, Long> { }
