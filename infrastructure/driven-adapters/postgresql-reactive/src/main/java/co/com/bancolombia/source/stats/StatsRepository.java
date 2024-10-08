package co.com.bancolombia.source.stats;

import co.com.bancolombia.source.stats.dto.StatsDTO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends R2dbcRepository<StatsDTO, Long> { }